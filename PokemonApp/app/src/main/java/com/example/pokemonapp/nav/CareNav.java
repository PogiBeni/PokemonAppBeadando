package com.example.pokemonapp.nav;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokemonapp.R;
import com.example.pokemonapp.classes.FileManager;
import com.example.pokemonapp.classes.PokemonData;
import com.example.pokemonapp.classes.StaticDataOuter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CareNav extends Fragment {

    int setPokemonId = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_care_nav, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button previous = view.findViewById(R.id.Previous);
        Button next = view.findViewById(R.id.Next);
        Button pet = view.findViewById(R.id.Pet);
        ImageButton delete = view.findViewById(R.id.DeletePokemon);
        ImageButton dice = view.findViewById(R.id.Dice);

        ArrayList<PokemonData> pokemonCaughtList = StaticDataOuter.StaticData.pokemonCaughtList;

        setScreen(view, pokemonCaughtList);

        previous.setOnClickListener(View -> previousId(view, pokemonCaughtList));
        next.setOnClickListener(View -> nextId(view, pokemonCaughtList));
        dice.setOnClickListener(View -> diceRoll(view, pokemonCaughtList));
        pet.setOnClickListener(View -> petPokemon(pokemonCaughtList));
        delete.setOnClickListener(View -> deletePokemon(view, pokemonCaughtList));

    }
    public void previousId(View view, ArrayList<PokemonData> pokemonCaughtList)
    {
        if (setPokemonId != 0) {
            setPokemonId -= 1;
            setScreen(view, pokemonCaughtList);
        }
    }

    public void nextId(View view, ArrayList<PokemonData> pokemonCaughtList)
    {
        if (setPokemonId != pokemonCaughtList.size() - 1) {
            setPokemonId += 1;
            setScreen(view, pokemonCaughtList);
        }
    }

    public void diceRoll(View view, ArrayList<PokemonData> pokemonCaughtList)
    {
        if(pokemonCaughtList.size() == 0) return;
        Random rnd = new Random();
        setPokemonId = rnd.nextInt(pokemonCaughtList.size());
        setScreen(view, pokemonCaughtList);
    }
    public void petPokemon(ArrayList<PokemonData> pokemonCaughtList)
    {
        if(pokemonCaughtList.size() == 0) return;
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(50);
    }

    public void deletePokemon(View view,ArrayList<PokemonData> pokemonCaughtList) {
        if(pokemonCaughtList.size() == 0) return;

        FileManager fileManager = new FileManager();
        fileManager.deleteFromCaughtList(setPokemonId);

        setPokemonId = 0;
        setScreen(view,pokemonCaughtList);
    }
    public void setScreen(View view, ArrayList<PokemonData> pokemonCaughtList)
    {
        ImageView imageView = view.findViewById(R.id.imageViewCare);
        TextView name = view.findViewById(R.id.textViewName);
        if (pokemonCaughtList.size() == 0) {
            Glide.with(view).load(R.drawable.questionmark).into(imageView);
            name.setText("No pokemon caught!");

        } else {
            Glide.with(view).load(pokemonCaughtList.get(setPokemonId).ImageLink).into(imageView);
            name.setText(pokemonCaughtList.get(setPokemonId).Name);
        }
    }
}