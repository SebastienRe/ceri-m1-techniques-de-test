package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IPokemonTrainerFactoryTest {

    IPokedexFactory pokedexFactory;
    IPokemonTrainerFactory pokemonTrainerFactory;
    PokemonTrainer pokemonTrainer;
    @BeforeEach
    public void setUp() {
        pokedexFactory = mock(IPokedexFactory.class);
        pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
        pokemonTrainer = mock(PokemonTrainer.class);
    }

    @Test
    public void testCreateTrainer() {
        when(pokemonTrainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory))
                .thenReturn(pokemonTrainer);

        assertEquals(pokemonTrainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory),
                pokemonTrainer);
    }
}