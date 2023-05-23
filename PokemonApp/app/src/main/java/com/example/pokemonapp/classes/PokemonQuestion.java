package com.example.pokemonapp.classes;

import java.util.ArrayList;

public class PokemonQuestion {
    public String question,goodAnswer,url,type,name;
    public ArrayList<String> wrongAnswers;

    public PokemonQuestion(String question, String goodAnswer, String url, ArrayList<String> wrongAnswers, String type, String name) {
        this.question = question;
        this.url = url;
        this.goodAnswer = goodAnswer;
        this.wrongAnswers = wrongAnswers;
        this.type = type;
        this.name = name;
    }
}
