package com.example.pokemonapp.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonResult {

    @SerializedName("name")
    public String name;

    @SerializedName("weight")
    public int weight;

    @SerializedName("types")
    public List<PokemonTypes> types;

    @SerializedName("sprites")
    public Sprites sprites;


}
