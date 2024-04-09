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
        pokemonFactory = new PokemonFactory();
        metadataProvider = mock(IPokemonMetadataProvider.class);
        Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
    }

    @Test
    void createPokemon() {
        Pokemon a = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        assertEquals(a.getIndex(), 0);
        assertEquals(a.getCp(), 613);
        assertEquals(a.getHp(), 64);
        assertEquals(a.getDust(), 4000);
        assertEquals(a.getCandy(), 4);
    }

    @Test
    void createPokemonShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(-1, 100, 100, 1000, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(151, 100, 100, 1000, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(0, -1, 100, 1000, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(0, 100, -1, 1000, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(0, 100, 100, -1, 10);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(0, 100, 100, 1000, -1);
        });

    }

    @Test
    void testGetAttack(){
        assertEquals(Aquali.getAttack(), 186);
    }

    @Test
    void testGetDefense(){
        assertEquals(Aquali.getDefense(), 168);
    }

    @Test
    void testGetStamina(){
        assertEquals(Aquali.getStamina(), 260);
    }


    @Test
    void testGetCp(){
        assertEquals(Aquali.getCp(), 2729);
    }

    @Test
    void testGetHp(){
        assertEquals(Aquali.getHp(), 202);
    }

    @Test
    void testGetDust(){
        assertEquals(Aquali.getDust(), 5000);
    }

    @Test
    void testGetCandy(){
        assertEquals(Aquali.getCandy(), 4);
    }

    @Test
    void testGetIv(){
        assertEquals(Aquali.getIv(), 100);
    }

}