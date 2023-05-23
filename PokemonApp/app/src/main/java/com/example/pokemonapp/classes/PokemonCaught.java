package com.example.pokemonapp.classes;

import android.util.Log;

import com.example.pokemonapp.classes.PokemonData;

import java.util.ArrayList;

public class PokemonCaught {

    public ArrayList<PokemonData> caughtPokemons;


    public PokemonCaught() {
        this.caughtPokemons = new ArrayList<PokemonData>();
    }

    public void newCaughtPokemon(PokemonData e) {

        boolean exists = false;
        String result="";

        for (PokemonData i : caughtPokemons) {

            if (e.getName() == i.getName()) {
                exists = true;
            }
        }
        if (!exists) {
            caughtPokemons.add(e);

            for (PokemonData i: caughtPokemons) {
                result+= i.getName() + " | "+ i.getImageLink()+"\n";
            }

            Log.d("PokemonCaught",result);
        }

    }

}
