package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;

class IPokemonMetadataProviderTest {

    PokemonMetadata MetaAquali;
    @BeforeEach
    public void setUp() {
         MetaAquali = new PokemonMetadata(133, "Aquali", 186, 168, 260);
    }
    @Test
    void getPokemonMetadata() throws PokedexException {
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);

        when(metadataProvider.getPokemonMetadata(133))
                .thenReturn(MetaAquali);
        assertEquals(metadataProvider.getPokemonMetadata(133), MetaAquali);
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


    @Test
    void testgetIndex(){
        assertEquals(MetaAquali.getIndex(), 133);
    }

    @Test
    void testGetName(){
        assertEquals(MetaAquali.getName(), "Aquali");
    }

    @Test
    void testGetAttack(){
        assertEquals(MetaAquali.getAttack(), 186);
    }

    @Test
    void testGetDefense(){
        assertEquals(MetaAquali.getDefense(), 168);
    }

    @Test
    void testGetStamina(){
        assertEquals(MetaAquali.getStamina(), 260);
    }

    @Test
    void getPokemonMetadataExistingIndexAquali() throws PokedexException {
        PokemonMetadataProvider provider = new PokemonMetadataProvider();
        PokemonMetadata metadata = provider.getPokemonMetadata(133);
        assertEquals(133, metadata.getIndex());
        assertEquals("Aquali", metadata.getName());
        assertEquals(186, metadata.getAttack());
        assertEquals(168, metadata.getDefense());
        assertEquals(260, metadata.getStamina());
    }

    @Test
    void getPokemonMetadataExistingIndexBulbasaur() throws PokedexException {
        PokemonMetadataProvider provider = new PokemonMetadataProvider();
        PokemonMetadata metadata = provider.getPokemonMetadata(0);
        assertEquals(0, metadata.getIndex());
        assertEquals("Bulbasaur", metadata.getName());
        assertEquals(126, metadata.getAttack());
        assertEquals(126, metadata.getDefense());
        assertEquals(90, metadata.getStamina());
    }

    @Test
    void getPokemonMetadataNonExistingIndex() {
        PokemonMetadataProvider provider = new PokemonMetadataProvider();
        assertThrows(PokedexException.class, () -> {
            provider.getPokemonMetadata(999);
        });
    }
}