package com.example.pokemonapp.nav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokemonapp.MainActivity;
import com.example.pokemonapp.classes.PokemonAdapter;
import com.example.pokemonapp.R;
import com.example.pokemonapp.classes.StaticDataOuter;

public class PokeballNav extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokeball_nav, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewPokeball);
        PokemonAdapter pokemonAdapter = new PokemonAdapter(StaticDataOuter.StaticData.pokemonCaughtList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pokemonAdapter);
        return view;
    }
}