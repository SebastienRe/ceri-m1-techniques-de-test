package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IPokemonFactoryTest {

    IPokemonFactory pokemonFactory;
    IPokemonMetadataProvider metadataProvider;
    Pokemon Aquali;

    @BeforeEach
    public void setUp() {
        pokemonFactory = mock(IPokemonFactory.class);
        metadataProvider = mock(IPokemonMetadataProvider.class);
        Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
    }

    @Test
    void createPokemon() {

        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4))
                .thenReturn(Aquali);

        assertEquals(pokemonFactory.createPokemon(0, 613, 64, 4000, 4), Aquali);
    }
}