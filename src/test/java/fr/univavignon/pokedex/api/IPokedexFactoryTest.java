package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IPokedexFactoryTest {

    IPokedexFactory pokedexFactory;
    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;
    IPokedex pokedex;

    @BeforeEach
    public void setUp() {
        pokedexFactory = mock(IPokedexFactory.class);
        metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
        pokedex = mock(IPokedex.class);
    }

    @Test
    public void testCreatePokedex() {
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory))
                .thenReturn(pokedex);

        assertEquals(pokedex, pokedexFactory.createPokedex(metadataProvider,
                pokemonFactory));
    }

    @Test
    void createPokemonValidParameters() {
        PokemonFactory factory = new PokemonFactory();
        Pokemon pokemon = factory.createPokemon(0, 100, 100, 1000, 10);
        assertEquals(0, pokemon.getIndex());
        assertEquals(100, pokemon.getCp());
        assertEquals(100, pokemon.getHp());
        assertEquals(1000, pokemon.getDust());
        assertEquals(10, pokemon.getCandy());
    }

    @Test
    void createPokemonInvalidParameters() {
        PokemonFactory factory = new PokemonFactory();
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPokemon(-1, 100, 100, 1000, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPokemon(151, 100, 100, 1000, 10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPokemon(0, -1, 100, 1000, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPokemon(0, 100, -1, 1000, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPokemon(0, 100, 100, -1, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            factory.createPokemon(0, 100, 100, 1000, -1);
        });
    }
}