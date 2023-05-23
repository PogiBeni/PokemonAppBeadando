package com.example.pokemonapp.nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapp.classes.PokemonAdapter;
import com.example.pokemonapp.R;
import com.example.pokemonapp.classes.StaticDataOuter;

public class BookNav extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_nav, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBook);
        PokemonAdapter pokemonAdapter = new PokemonAdapter(StaticDataOuter.StaticData.pokemonList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(pokemonAdapter);
        return view;
    }

}