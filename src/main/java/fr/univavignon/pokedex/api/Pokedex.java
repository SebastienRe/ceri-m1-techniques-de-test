package fr.univavignon.pokedex.api;

import java.util.List;

import java.util.ArrayList;
import java.util.Comparator;

public class Pokedex implements IPokedex {

    private PokemonMetadataProvider pokemonMetadataProvider;
    private PokemonFactory pokemonFactory;
    private List<Pokemon> pokemons = new ArrayList<>();

    public Pokedex(IPokemonMetadataProvider pokemonMetadataProvider, IPokemonFactory pokemonFactory) {
        this.pokemonMetadataProvider = (PokemonMetadataProvider) pokemonMetadataProvider;
        this.pokemonFactory = (PokemonFactory) pokemonFactory;
    }

    @Override
    public int size() {
        return pokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        return pokemons.size() - 1;
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < 0 || id >= pokemons.size()) {
            throw new PokedexException("Invalid index");
        }
        return pokemons.get(id);
    }

    @Override
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        pokemons.sort(order);
        return pokemons;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        Pokemon incompletePokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);
        try {
            PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(index);
            return new Pokemon(
                    metadata.getIndex(),
                    metadata.getName(),
                    metadata.getAttack()  + (int) (Math.random() * 15),
                    metadata.getDefense()  + (int) (Math.random() * 15),
                    metadata.getStamina() + (int) (Math.random() * 15),
                    incompletePokemon.getCp(),
                    incompletePokemon.getHp(),
                    incompletePokemon.getDust(),
                    incompletePokemon.getCandy(),
                    (int) (Math.random() * 100)
            );
        } catch (PokedexException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return pokemonMetadataProvider.getPokemonMetadata(index);
    }
}
