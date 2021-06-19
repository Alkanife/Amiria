package fr.alkanife.amiria.lang;

import fr.alkanife.amiria.characters.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum Locale {

    FRENCH,
    ENGLISH;

    private HashMap<String, Object> translations;
    private List<Character> characters;

    Locale() {
        this.translations = new HashMap<>();
        this.characters = new ArrayList<>();
    }

    public HashMap<String, Object> getTranslations() {
        return translations;
    }

    public void setTranslations(HashMap<String, Object> translations) {
        this.translations = translations;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}