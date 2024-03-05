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
    private Pokemon Aquali;
    private Pokemon Bulbizarre;

    @BeforeEach
    void setUp() {
        pokedex = mock(IPokedex.class);
        Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        Bulbizarre = new Pokemon(1, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
    }

    @Test
    void size() {
        when(pokedex.size()).thenReturn(0);
        assertEquals(0, pokedex.size());

        pokedex.addPokemon(Aquali);

        when(pokedex.size()).thenReturn(1);
        assertEquals(1, pokedex.size());

        pokedex.addPokemon(Bulbizarre);

        when(pokedex.size()).thenReturn(2);
        assertEquals(2, pokedex.size());
    }

    @Test
    void addPokemon() {
        when(pokedex.addPokemon(Aquali)).thenReturn(0);
        int index = pokedex.addPokemon(Aquali);

        assertEquals(0, index);
    }

    @Test
    void getPokemon() throws PokedexException {
        when(pokedex.getPokemon(0)).thenReturn(Aquali);
        pokedex.addPokemon(Aquali);
        Pokemon pokemon = pokedex.getPokemon(0);

        assertEquals(Aquali, pokemon);
    }

    @Test
    void getPokemonShouldThrowPokedexException() throws PokedexException {
        when(pokedex.getPokemon(0)).thenThrow(new PokedexException("Pokemon not found"));
        assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(0);
        });
    }

    @Test
    void getPokemons() {
        pokedex.addPokemon(Aquali);
        pokedex.addPokemon(Bulbizarre);
        when(pokedex.getPokemons()).thenReturn(Arrays.asList(Aquali, Bulbizarre));
        List<Pokemon> pokemons = pokedex.getPokemons();

        assertEquals(2, pokemons.size());
        assertEquals(Aquali, pokemons.get(0));
        assertEquals(Bulbizarre, pokemons.get(1));
    }

    @Test
    void testGetPokemons() {
        pokedex.addPokemon(Aquali);
        pokedex.addPokemon(Bulbizarre);
        Comparator<Pokemon> order = (p1, p2) -> p1.getIndex() - p2.getIndex();
        when(pokedex.getPokemons(order)).thenReturn(Arrays.asList(Bulbizarre, Aquali));
        List<Pokemon> pokemonsSorted = pokedex.getPokemons(order);

        assertEquals(2, pokemonsSorted.size());
        assertEquals(Bulbizarre, pokemonsSorted.get(0));
        assertEquals(Aquali, pokemonsSorted.get(1));
    }
}