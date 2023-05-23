package com.example.pokemonapp.classes;

import android.util.Log;

import com.example.pokemonapp.classes.PokemonData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    public void saveNewPokemon(PokemonData caughtPokemon, File listFile) {
        try {
            FileOutputStream writer = new FileOutputStream(listFile, true);
            writer.write((caughtPokemon.Name + " | ").getBytes());
            writer.write((caughtPokemon.Type + " | ").getBytes());
            writer.write((caughtPokemon.ImageLink + " \n").getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createFile(File listFile)
    {
        try {
            if (listFile.createNewFile()) {
                Log.d("Alma","File created: " + listFile.getName());
            } else {
                Log.d("Alma","File already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<PokemonData> loadCaughtList(File file)
    {
        ArrayList<PokemonData> pokemonCaughtList = new ArrayList<>();
        String content = "";
        try {

            Scanner scanner = new Scanner(file);
            scanner.reset();
            while (scanner.hasNextLine()) {
                content += scanner.nextLine() +"\n";
            }
            scanner.close();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(content.length() != 0)
        {
            String[] parts = content.split("\\n");
            for (String i : parts) {
                pokemonCaughtList.add(new PokemonData(i.split("\\|")[0].trim(), i.split("\\|")[1].trim(), i.split("\\|")[2].trim()));
            }
        }
        return pokemonCaughtList;
    }
    public void deleteFromCaughtList(int pokemonId)
    {
        File file = StaticDataOuter.StaticData.listFile;
        StaticDataOuter.StaticData.pokemonCaughtList.remove(pokemonId);
        try {
            FileOutputStream writer = new FileOutputStream(file);
            for (PokemonData pokemon: StaticDataOuter.StaticData.pokemonCaughtList)
            {
                writer.write((pokemon.Name + " | ").getBytes());
                writer.write((pokemon.Type + " | ").getBytes());
                writer.write((pokemon.ImageLink + " \n").getBytes());
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
