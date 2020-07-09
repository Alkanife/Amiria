package fr.alkanife.amiria.character;

import java.util.ArrayList;
import java.util.List;

public class CharacterManager {

    public List<Character> characters;

    public CharacterManager() {
        characters = new ArrayList<>();

        characters.add(new Adamai());
        characters.add(new Akuma());
        characters.add(new Alexia());
    }
    
    public Character getCharacter(String name) {
        Character character = null;

        for (Character character1 : characters)
            if (character1.name().toLowerCase().startsWith(name.toLowerCase()))
                character = character1;

        return character;
    }

}
