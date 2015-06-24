
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Sluggify {

    private static final Map<String, String> slugifyCache = new ConcurrentHashMap<>();

    public static boolean isEmpty(String stringToCheck) {
        return stringToCheck == null || stringToCheck.isEmpty();
    }

    public static String noAccents(String string) {
        return string.replaceAll("[ä]", "ae").replaceAll("[ö]",
                "oe").replaceAll("[ü]", "ue").replaceAll("[Ä]", "Ae").replaceAll("[Ö]", "Oe").replaceAll("[Ū]",
                "Ue").replaceAll("[ß]", "ss").replaceAll("[àáâãäåāąă]", "a").replaceAll("[çćčĉċ]", "c").replaceAll("[ďđð]", "d").replaceAll("[èéêëēęěĕė]",
                "e").replaceAll("[ƒſ]", "f").replaceAll("[ĝğġģ]", "g").replaceAll("[ĥħ]",
                "h").replaceAll("[ìíîïīĩĭįı]", "i").replaceAll("[ĳĵ]", "j").replaceAll("[ķĸ]",
                "k").replaceAll("[łľĺļŀ]", "l").replaceAll("[ñńňņŉŋ]", "n").replaceAll("[òóôõöøōőŏœ]",
                "o").replaceAll("[Þþ]", "p").replaceAll("[ŕřŗ]", "r").replaceAll("[śšşŝș]",
                "s").replaceAll("[ťţŧț]", "t").replaceAll("[ùúûüūůűŭũų]", "u").replaceAll("[ŵ]",
                "w").replaceAll("[ýÿŷ]", "y").replaceAll("[žżź]", "z").replaceAll("[æ]", "ae").
                replaceAll("[ÀÁÂÃÄÅĀĄĂ]", "A").replaceAll("[ÇĆČĈĊ]", "C").replaceAll("[ĎĐÐ]", "D").
                replaceAll("[ÈÉÊËĒĘĚĔĖ]", "E").replaceAll("[ĜĞĠĢ]", "G").replaceAll("[ĤĦ]", "H").
                replaceAll("[ÌÍÎÏĪĨĬĮİ]", "I").replaceAll("[Ĵ]", "J").replaceAll("[Ķ]", "K").
                replaceAll("[ŁĽĹĻĿ]", "L").replaceAll("[ÑŃŇŅŊ]", "N").replaceAll("[ÒÓÔÕÖØŌŐŎ]", "O").
                replaceAll("[ŔŘŖ]", "R").replaceAll("[ŚŠŞŜȘ]", "S").replaceAll("[ÙÚÛÜŪŮŰŬŨŲ]", "U").
                replaceAll("[Ŵ]", "W").replaceAll("[ÝŶŸ]", "Y").replaceAll("[ŹŽŻ]", "Z").replaceAll("[ß]", "ss").
                replaceAll("[+]", "plus");
    }

    public static String sluggify(String string) {
        if (isEmpty(string)) {
            return string;
        }

        if (!slugifyCache.containsKey(string)) {
            slugifyCache.put(string, doSlugify(string));
        }

        return slugifyCache.get(string);
    }

    private static String doSlugify(String string) {
        if (string == null) return null;

        string = noAccents(string);

        string = string.replaceAll("([a-z])'s([^a-z])", "$1s$2");
        string = string.replaceAll("[^\\w]", "-").replaceAll("-{2,}", "-");
        string = string.replaceAll("-+$", "").replaceAll("^-+", "");

        return string.toLowerCase();
    }
}