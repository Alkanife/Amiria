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
        characters.add(new Aliedad());
        characters.add(new Amalia());
        characters.add(new Andrea());
        characters.add(new Anoriel());
        characters.add(new Asarim());
        characters.add(new Bimai());
        characters.add(new Christopher());
        characters.add(new DarkenSheele());
        characters.add(new Endal());
        characters.add(new Enyxia());
        characters.add(new Extasia());
        characters.add(new Gaia());
        characters.add(new Gosutookami());
        characters.add(new Hanyuu());
        //Jessica Grey
        characters.add(new Kanna());
        characters.add(new Kashya());
        characters.add(new Kass());
        characters.add(new Kei());
        characters.add(new Kudo());
        characters.add(new Kuyu());
        characters.add(new Mahito());
        characters.add(new Marla());
        characters.add(new Maskai());
        characters.add(new Math());
        characters.add(new MathSpirit());
        characters.add(new Matthias());
        characters.add(new Maureen());
        characters.add(new Midorai());
        characters.add(new Morgiana());
        characters.add(new Nagoramatio());
        characters.add(new Nerias());
        characters.add(new Nikolai());
        characters.add(new Raizan());
        characters.add(new Ravenn());
        characters.add(new Rika());
    }
    
    public Character getCharacter(String name) {
        Character character = null;

        for (Character character1 : characters)
            if (character1.name().toLowerCase().startsWith(name.toLowerCase()))
                character = character1;

        return character;
    }

}
