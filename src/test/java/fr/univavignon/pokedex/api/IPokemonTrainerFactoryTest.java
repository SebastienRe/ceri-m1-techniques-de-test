package fr.univavignon.pokedex.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class IPokemonTrainerFactoryTest {
    IPokemonTrainerFactory pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);

    @BeforeEach
    public void setUp() {
        pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
    }

    @Test
    public void testCreateTrainer() {
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);
        when(pokemonTrainerFactory.createTrainer("Michel", Team.INSTINCT, pokedexFactory)).thenReturn(
                new PokemonTrainer("Michel", Team.INSTINCT, mock(IPokedex.class))
        );
        PokemonTrainer trainer = pokemonTrainerFactory.createTrainer("Michel", Team.INSTINCT, pokedexFactory);
        assertNotNull(trainer);
        assertEquals("Michel", trainer.getName());
        assertEquals(Team.INSTINCT, trainer.getTeam());
    }
}