package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IPokemonMetadataProviderTest {

    @Test
    void getPokemonMetadata() throws PokedexException {
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        PokemonMetadata Aquali = new PokemonMetadata(133, "Aquali", 186, 168, 260);
        when(metadataProvider.getPokemonMetadata(133))
                .thenReturn(Aquali);
        assertEquals(metadataProvider.getPokemonMetadata(133), Aquali);
    }

    @Test
    void getPokemonMetadataShouldThrowPokedexException() throws PokedexException {
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        when(metadataProvider.getPokemonMetadata(133))
                .thenThrow(new PokedexException("Pokemon not found"));
        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(133);
        });
    }
}