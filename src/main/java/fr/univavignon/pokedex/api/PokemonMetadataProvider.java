package fr.univavignon.pokedex.api;

import java.util.List;
import java.util.ArrayList;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    /*
    List<PokemonMetadata> pokemonsMetadata = new ArrayList<>();


    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 0 || index >= pokemonsMetadata.size()) {
            throw new PokedexException("Invalid index");
        }
        return pokemonsMetadata.get(index);

    }
    */

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
