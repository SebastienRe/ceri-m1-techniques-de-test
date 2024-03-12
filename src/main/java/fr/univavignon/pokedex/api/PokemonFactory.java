package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory {
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        if (index < 0 || index > 150) throw new IllegalArgumentException("Invalid index");
        if (cp < 0 || hp < 0 || dust < 0 || candy < 0) throw new IllegalArgumentException("Invalid stats");
        return new Pokemon(index, "", 0, 0, 0, cp, hp, dust, candy, 0);
    }

}
