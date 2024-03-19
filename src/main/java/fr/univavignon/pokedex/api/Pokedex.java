package fr.univavignon.pokedex.api;

import java.util.List;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The Pokedex class represents a collection of Pokemon.
 * It implements the IPokedex interface.
 */
public class Pokedex implements IPokedex {

    private final PokemonMetadataProvider pokemonMetadataProvider;
    private final PokemonFactory pokemonFactory;
    private final List<Pokemon> pokemons = new ArrayList<>();

    /**
     * Constructs a Pokedex object with the specified Pokemon metadata provider and
     * Pokemon factory.
     *
     * @param pokemonMetadataProvider The provider for Pokemon metadata.
     * @param pokemonFactory          The factory for creating Pokemon objects.
     */
    public Pokedex(IPokemonMetadataProvider pokemonMetadataProvider, IPokemonFactory pokemonFactory) {
        this.pokemonMetadataProvider = (PokemonMetadataProvider) pokemonMetadataProvider;
        this.pokemonFactory = (PokemonFactory) pokemonFactory;
    }

    /**
     * Returns the number of Pokemon in the Pokedex.
     *
     * @return The number of Pokemon.
     */
    @Override
    public int size() {
        return pokemons.size();
    }

    /**
     * Adds a Pokemon to the Pokedex.
     *
     * @param pokemon The Pokemon to add.
     * @return The index of the added Pokemon.
     */
    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        return pokemons.size() - 1;
    }

    /**
     * Retrieves a Pokemon from the Pokedex based on its index.
     *
     * @param id The index of the Pokemon.
     * @return The Pokemon with the specified index.
     * @throws PokedexException If the index is invalid.
     */
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < 0 || id >= pokemons.size()) {
            throw new PokedexException("Invalid index");
        }
        return pokemons.get(id);
    }

    /**
     * Returns a list of all the Pokemon in the Pokedex.
     *
     * @return The list of Pokemon.
     */
    @Override
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    /**
     * Returns a sorted list of all the Pokemon in the Pokedex based on the
     * specified order.
     *
     * @param order The comparator used for sorting the Pokemon.
     * @return The sorted list of Pokemon.
     */
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        pokemons.sort(order);
        return pokemons;
    }

    /**
     * Creates a new Pokemon with the specified attributes.
     *
     * @param index The index of the Pokemon.
     * @param cp    The combat power of the Pokemon.
     * @param hp    The hit points of the Pokemon.
     * @param dust  The amount of dust required to power up the Pokemon.
     * @param candy The amount of candy required to evolve the Pokemon.
     * @return The created Pokemon.
     */
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        Pokemon incompletePokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);
        try {
            PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(index);
            if (metadata == null)
                throw new PokedexException("Invalid index");
            return new Pokemon(
                    metadata.getIndex(),
                    metadata.getName(),
                    metadata.getAttack() + (int) (Math.random() * 15),
                    metadata.getDefense() + (int) (Math.random() * 15),
                    metadata.getStamina() + (int) (Math.random() * 15),
                    incompletePokemon.getCp(),
                    incompletePokemon.getHp(),
                    incompletePokemon.getDust(),
                    incompletePokemon.getCandy(),
                    (int) (Math.random() * 100));
        } catch (PokedexException e) {
            return null;
        }
    }

    /**
     * Retrieves the metadata of a Pokemon based on its index.
     *
     * @param index The index of the Pokemon.
     * @return The metadata of the Pokemon.
     * @throws PokedexException If the index is invalid.
     */
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return pokemonMetadataProvider.getPokemonMetadata(index);
    }
}