package com.example.pokemonapp.classes;

public class PokemonData {
    public String Name, Type, ImageLink;

    public PokemonData(String name, String type, String imageLink) {
        Name = name;
        Type = type;
        ImageLink = imageLink;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }
}
