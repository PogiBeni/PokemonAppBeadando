package com.example.pokemonapp.classes;

import com.example.pokemonapp.classes.PokemonData;

import java.io.File;
import java.util.ArrayList;

public class StaticDataOuter {
    public static class StaticData {

        public static ArrayList<PokemonData> pokemonList = new ArrayList<>();
        public static File listFile;
        public static ArrayList<PokemonData> pokemonCaughtList;

    }
}
