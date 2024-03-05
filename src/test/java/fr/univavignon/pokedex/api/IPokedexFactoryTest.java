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
}