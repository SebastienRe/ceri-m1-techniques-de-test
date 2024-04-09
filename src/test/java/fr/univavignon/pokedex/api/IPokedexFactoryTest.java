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
        pokedexFactory = new PokedexFactory();
        metadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();
        pokedex = mock(IPokedex.class);
    }

    @Test
    public void testCreatePokedex() {

        Pokedex a = (Pokedex) pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertNotNull(a);
    }

}