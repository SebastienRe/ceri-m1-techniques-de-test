package fr.univavignon.pokedex.api;

import java.util.List;
import java.util.ArrayList;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        switch (index){
            case 0:
                return new PokemonMetadata(0, "Bulbasaur", 126, 126, 90);
            case 133:
                return new PokemonMetadata(133, "Aquali", 186, 168, 260);
            default:
                throw new PokedexException("Pokemon not found");
        }

    }
}
