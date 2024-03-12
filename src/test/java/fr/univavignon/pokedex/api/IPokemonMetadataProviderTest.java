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

    //test getters de PokemonMetadata
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
}