package fr.alkanife.amiria.lang;

import de.cerus.jdasc.interaction.Interaction;
import fr.alkanife.amiria.Amiria;
import fr.alkanife.amiria.Errors;
import fr.alkanife.amiria.Logs;
import fr.alkanife.amiria.YmlReader;
import net.dv8tion.jda.api.entities.Role;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

public class Lang {

    public static void load() {
        load(false);
    }

    public static boolean load(boolean reload) {
        int errorsThen = Errors.errors;

        for (Locale locale : Locale.values()) {

            try {
                HashMap<String, Object> data = YmlReader.read(locale.toString().toLowerCase(java.util.Locale.ROOT));

                if (data == null) {
                    Errors.error("Data is null");
                    return false;
                }

                locale.setTranslations(data);
            } catch (Exception exception) {
                Errors.error("Error while reading YML File", exception);
            }

            Logs.info((reload ? "(reload) " : "") + "Loaded language " + locale.name() + ", " + locale.getTranslations().size() + " translations");
        }

        return errorsThen == Errors.errors;
    }

    public static Locale findLocale(Interaction interaction) {
        boolean english = false;

        List<Role> roles = interaction.getMember().getRoles();

        if (roles.size() == 0)
            return Locale.FRENCH;

        for (Role role : roles)
            if (role.getId().equals(Amiria.getEnglish().getId()))
                english = true;

        if (english)
            return Locale.ENGLISH;
        else
            return Locale.FRENCH;
    }

    public static String tl(Interaction interaction, String key, String... values) {
        return tl(findLocale(interaction), key, values);
    }

    public static String tl(Locale locale, String key, String... values) {
        if (locale.getTranslations().containsKey(key)) {
            MessageFormat messageFormat = new MessageFormat(String.valueOf(locale.getTranslations().get(key)));
            return messageFormat.format(values);
        } else return "{Missing " + locale.name() + " translation, " + key + "}";
    }

}
