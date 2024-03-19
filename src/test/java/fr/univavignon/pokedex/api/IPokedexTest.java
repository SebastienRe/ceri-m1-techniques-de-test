package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IPokedexTest {

    private IPokedex pokedex;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;
    private Pokemon Aquali;
    private Pokemon Bulbizarre;

    private PokemonComparators comparators;

    @BeforeEach
    void setUp() throws PokedexException {
        metadataProvider = mock(PokemonMetadataProvider.class);
        when(metadataProvider.getPokemonMetadata(133)).thenReturn(new PokemonMetadata(133, "Aquali", 186, 168, 260));
        when(metadataProvider.getPokemonMetadata(1)).thenReturn(new PokemonMetadata(1, "Bulbizarre", 126, 126, 90));
        pokemonFactory = mock(PokemonFactory.class);
        when(pokemonFactory.createPokemon(133, 100, 100, 1000, 10)).thenReturn(Aquali);
        when(pokemonFactory.createPokemon(1, 100, 100, 1000, 10)).thenReturn(Bulbizarre);

        pokedex = new Pokedex(
                metadataProvider,
                pokemonFactory);
        Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        Bulbizarre = new Pokemon(1, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
    }

    @Test
    void size() {
        assertEquals(0, pokedex.size());
    }

    @Test
    void addPokemon() {
        int index = pokedex.addPokemon(Aquali);
        assertEquals(0, index);
    }

    @Test
    void getPokemon() throws PokedexException {
        pokedex.addPokemon(Aquali);
        Pokemon pokemon = pokedex.getPokemon(0);

        assertEquals(Aquali, pokemon);
    }


    @Test
    void getPokemonShouldThrowPokedexException() throws PokedexException {
        assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(0);
        });
    }

    @Test
    void getPokemonShouldThrowPokedexExceptionForInvalidIndex() throws PokedexException {
        assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(-1);
        });
    }

    @Test
    void getPokemons() {
        pokedex.addPokemon(Aquali);
        pokedex.addPokemon(Bulbizarre);
        List<Pokemon> pokemons = pokedex.getPokemons();

        assertEquals(2, pokemons.size());
        assertEquals(Aquali, pokemons.get(0));
        assertEquals(Bulbizarre, pokemons.get(1));
    }

    @Test
    void testGetPokemons() {
        pokedex.addPokemon(Aquali);
        pokedex.addPokemon(Bulbizarre);
        comparators = PokemonComparators.CP;
        List<Pokemon> pokemonsSorted = pokedex.getPokemons(comparators);

        assertEquals(2, pokemonsSorted.size());
        assertEquals(Bulbizarre, pokemonsSorted.get(0));
        assertEquals(Aquali, pokemonsSorted.get(1));
    }

    @Test
    void createPokemon() throws PokedexException {
        PokemonMetadata metadata = new PokemonMetadata(10, "TestPokemon", 100, 100, 100);
        when(metadataProvider.getPokemonMetadata(10)).thenReturn(metadata);
        PokemonFactory pokemonFactory = mock(PokemonFactory.class);
        when(pokemonFactory.createPokemon(10, 100, 100, 1000, 10))
                .thenReturn(new Pokemon(10, "TestPokemon", 100, 100, 100, 1000, 10, 1000, 10, 100));
        IPokedex pokedex = new Pokedex(metadataProvider, pokemonFactory);

        Pokemon pokemon = pokedex.createPokemon(10, 100, 100, 1000, 10);

        assertEquals(10, pokemon.getIndex());
        assertEquals("TestPokemon", pokemon.getName());
        assertEquals(1000, pokemon.getCp());
        assertEquals(10, pokemon.getHp());
        assertEquals(1000, pokemon.getDust());
        assertEquals(10, pokemon.getCandy());

        assertTrue(pokemon.getIv() >= 0 && pokemon.getIv() <= 100);
        assertTrue(pokemon.getAttack() >= 100 && pokemon.getAttack() <= 115);
        assertTrue(pokemon.getDefense() >= 100 && pokemon.getDefense() <= 115);
        assertTrue(pokemon.getStamina() >= 100 && pokemon.getStamina() <= 115);
    }

    @Test
    void getPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = pokedex.getPokemonMetadata(133);
        assertEquals(133, metadata.getIndex());
        assertEquals("Aquali", metadata.getName());
        assertEquals(186, metadata.getAttack());
        assertEquals(168, metadata.getDefense());
        assertEquals(260, metadata.getStamina());
    }

    @Test
    void createPokemonShouldReturnNull() throws PokedexException {
        PokemonMetadata metadata = new PokemonMetadata(10, "TestPokemon", 100, 100, 100);
        when(metadataProvider.getPokemonMetadata(10)).thenReturn(metadata);
        PokemonFactory pokemonFactory = mock(PokemonFactory.class);
        when(pokemonFactory.createPokemon(10, 100, 100, 1000, 10))
                .thenReturn(new Pokemon(10, "TestPokemon", 100, 100, 100, 1000, 10, 1000, 10, 100));
        IPokedex pokedex = new Pokedex(metadataProvider, pokemonFactory);

        assertNull(pokedex.createPokemon(11, 100, 100, 1000, 10));
    }

}
