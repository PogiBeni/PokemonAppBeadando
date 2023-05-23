package com.example.pokemonapp;

import android.os.Bundle;
import android.util.Log;

import com.example.pokemonapp.classes.FileManager;
import com.example.pokemonapp.classes.PokemonClient;
import com.example.pokemonapp.classes.PokemonData;
import com.example.pokemonapp.classes.StaticDataOuter;
import com.example.pokemonapp.dtos.PokemonResult;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pokemonapp.databinding.ActivityMainBinding;

import java.io.File;
import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    FileManager fileManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StaticDataOuter.StaticData.listFile = new File(getApplicationContext().getFilesDir() +"/CaughtPokemonList");
        fileManager = new FileManager();
        fileManager.createFile(StaticDataOuter.StaticData.listFile);

        loadCaughtList.start();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(binding.getRoot());

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setItemIconTintList(null);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home_nav, R.id.book_nav, R.id.pokeball_nav, R.id.care_nav)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    protected void onStart( ) {
        super.onStart();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonClient client = retrofit.create(PokemonClient.class);
        for (int i = 1; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Response<PokemonResult> response = client.getPokemonByDexNumOrName(finalI).execute();
                    if (response.isSuccessful()) {
                        PokemonResult r = response.body();
                        String types = "";
                        if (r.types.size() > 0) {
                            types = r.types.get(0).type.name;
                            for (int j = 1; j < r.types.size(); j++) {
                                types += " / " + r.types.get(j).type.name;
                            }
                        }
                        StaticDataOuter.StaticData.pokemonList.add(new PokemonData(r.name, types, r.sprites.front_default));
                    }
                } catch (IOException e) {
                    Log.e("MainActivity", e.getMessage());
                }
            }).start();
        }
    }
    public Thread loadCaughtList = new Thread() {
        @Override
        public void run() {
            StaticDataOuter.StaticData.pokemonCaughtList = fileManager.loadCaughtList(StaticDataOuter.StaticData.listFile);
        }
    };
}