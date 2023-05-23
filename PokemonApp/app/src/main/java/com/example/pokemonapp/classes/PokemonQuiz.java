package com.example.pokemonapp.classes;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class PokemonQuiz {
    Random rnd;
    ArrayList<PokemonData> pokemonList;
    PokemonQuestion question;
    PokemonCaught caughtList;
    int pointCounter;
    PokemonQuestion catchablePokemon;
    File listFile;

    public PokemonQuiz(ArrayList<PokemonData> pokemonList,File file) {
        this.rnd = new Random();
        this.pokemonList = pokemonList;
        this.caughtList = new PokemonCaught();
        this.pointCounter = 0;
        this.listFile = file;
    }
    public int getPointCounter()
    {
        return pointCounter;
    }
    public PokemonQuestion getCurrentQuestion()
    {
        return question;
    }
    public boolean checkAnswer(String btnText) {

        boolean result = question.goodAnswer == btnText;
        if (result) {
            if(pointCounter == 0)
            {
                catchablePokemon = question;
            }
            pointCounter +=1;
            if(pointCounter == 5)
            {
                PokemonData caughtPokemon = new PokemonData(catchablePokemon.name, catchablePokemon.type, catchablePokemon.url);
                caughtList.newCaughtPokemon(caughtPokemon);
                pointCounter = 0;
                FileManager fileManager = new FileManager();
                fileManager.saveNewPokemon(caughtPokemon,listFile);
            }
            return true;
        }
        else {
            pointCounter = 0;
            return false;
        }
    }

    public void getRandomQuestion() {
        PokemonQuestion pQ;

        int randomGoodId = rnd.nextInt( pokemonList.size());
        String url = pokemonList.get(randomGoodId).ImageLink;
        String type = pokemonList.get(randomGoodId).Type;
        String name = pokemonList.get(randomGoodId).Name;
        String goodAnswer;
        String Question;
        ArrayList<String> wrongAnswers = new ArrayList<>();

        if(rnd.nextBoolean())
        {
            Question = "What is the name of this pokemon?";
            goodAnswer = pokemonList.get(randomGoodId).Name.replaceAll(" / ",",\n");
            for (int i = 0; i < 4; i++) {
                int randomWrongId;
                do {
                    randomWrongId = rnd.nextInt(pokemonList.size());
                } while (randomWrongId == randomGoodId);
                wrongAnswers.add(pokemonList.get(randomWrongId).Name);
            }
        }
        else {
            Question = "What is the type of this pokemon?";
            goodAnswer = pokemonList.get(randomGoodId).Type.replaceAll(" / ",",\n");
            for (int i = 0; i < 4; i++) {
                int randomWrongId;
                do {
                    randomWrongId = rnd.nextInt(pokemonList.size());
                } while (pokemonList.get(randomWrongId).Type.contains(pokemonList.get(randomGoodId).Type));
                wrongAnswers.add(pokemonList.get(randomWrongId).Type.replaceAll(" / ",",\n"));
            }
        }

        pQ = new PokemonQuestion(Question, goodAnswer, url, wrongAnswers,type,name);
        question = pQ;
    }

}