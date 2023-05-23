package com.example.pokemonapp.classes;

import static android.app.PendingIntent.getActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemonapp.R;
import com.example.pokemonapp.classes.PokemonData;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonHolder>{
    private ArrayList<PokemonData> pokemonList;

    public PokemonAdapter(ArrayList<PokemonData> pokemonList) {
        this.pokemonList = pokemonList;


    }
    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_row, parent, false);
        return new PokemonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {

        PokemonData pokemon = pokemonList.get(position);
        holder.txtName.setText(pokemon.getName());
        holder.txtType.setText(pokemon.getType());
        Glide.with(holder.itemView.getContext()).load(pokemon.ImageLink).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
    public class PokemonHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtType;
        public ImageView image;

        public PokemonHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtType = itemView.findViewById(R.id.txtType);
            image = itemView.findViewById(R.id.image);
        }
    }
}
