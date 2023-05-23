package com.example.pokemonapp.classes;

import com.example.pokemonapp.dtos.PokemonResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonClient {
    @GET("pokemon/{dexNumOrName}")
    Call<PokemonResult> getPokemonByDexNumOrName(@Path("dexNumOrName") int id);
}
