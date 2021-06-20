package fr.alkanife.amiria.characters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.alkanife.amiria.Errors;
import fr.alkanife.amiria.Logs;
import fr.alkanife.amiria.Utils;
import fr.alkanife.amiria.lang.Locale;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Characters {

    public static boolean load(boolean reload) {
        int errorsThen = Errors.errors;

        for (Locale locale : Locale.values()) {
            if (reload)
                locale.setCharacters(new ArrayList<>());

            try (Stream<Path> paths = Files.walk(Paths.get(Utils.absolutePath() + "/characters/" + locale.name().toLowerCase(java.util.Locale.ROOT) + ""))) {
                paths.filter(Files::isRegularFile).forEach(path -> {

                    try {
                        String raw = Files.readString(path);

                        Gson gson = new GsonBuilder().serializeNulls().create();

                        Character character = gson.fromJson(raw, Character.class);

                        locale.getCharacters().add(character);

                    } catch (IOException ioException) {
                        Errors.error("Error while reading file", ioException);
                    }
                });
            } catch (IOException ioException) {
                Errors.error("Error while walking in a directory. Make sure that the '/characters/french' and '/characters/english' folders exist.", ioException);
            }
        }

        for (Locale locale : Locale.values()) {
            int characters = locale.getCharacters().size();

            if (locale.getCharacters().size() > 0)
                Logs.info((reload ? "(reload) " : "") + "Loaded " + characters + " characters in " + locale.name());
        }

        return errorsThen == Errors.errors;
    }

    public static Character search(Locale locale, String name) {
        Character found = null;

        for (Character character : locale.getCharacters())
            if (character.getFull_name().toLowerCase(java.util.Locale.ROOT).contains(name.toLowerCase(java.util.Locale.ROOT)))
                found = character;

        return found;
    }

    public static List<Character> searchByAuthor(Locale locale, String name) {
        List<Character> characters = null;

        for (Character character : locale.getCharacters()) {
            if (character.getOriginal_owner().toLowerCase(java.util.Locale.ROOT).contains(name.toLowerCase(java.util.Locale.ROOT))) {
                if (characters == null)
                    characters = new ArrayList<>();
                characters.add(character);
            }
        }

        return characters;
    }



}
