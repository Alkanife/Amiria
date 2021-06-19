package fr.alkanife.amiria.lang;

import de.cerus.jdasc.interaction.Interaction;
import fr.alkanife.amiria.Amiria;
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

    public static void load(boolean reload) {
        for (Locale locale : Locale.values()) {
            HashMap<String, Object> data = YmlReader.read(locale.toString().toLowerCase(java.util.Locale.ROOT));

            if (data == null)
                return;

            locale.setTranslations(data);

            /*locale.setTranslations(new HashMap<>());

            for (String key : data.keySet())
                locale.getTranslations().put(key, String.valueOf(data.get(key)));*/

            Logs.info((reload ? "Reloaded" : "Loaded") + " language " + locale.name() + ", " + locale.getTranslations().size() + " translations");
        }

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
