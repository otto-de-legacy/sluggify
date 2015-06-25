package de.otto.sluggify;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sluggify {

    private static final Map<String, String> slugifyCache = new ConcurrentHashMap<>();

    public static boolean isEmpty(String stringToCheck) {
        return stringToCheck == null || stringToCheck.isEmpty();
    }

    private static final Pattern SPECIAL_CHARACTERS_REGEX = Pattern.compile("\\W");

    public static String removeSpecialCharactersAndConvertToLowercase(String input) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = SPECIAL_CHARACTERS_REGEX.matcher(input);
        int lastIdx = 0;
        while (matcher.find()) {
            int startIdx = matcher.start();
            if (startIdx > lastIdx) {
                result.append(input.substring(lastIdx, startIdx).toLowerCase());
            }
            char umlaut = matcher.group().charAt(0);
            String replacementString = replacementStringFor(umlaut);
            if (result.length() == 0 || !("-".equals(replacementString) && result.charAt(result.length() - 1) == '-')) {
                result.append(replacementString);
            }
            lastIdx = matcher.end();
        }
        if (lastIdx < input.length()) {
            result.append(input.substring(lastIdx).toLowerCase());
        }
        return result.toString();
    }

    private static String replacementStringFor(char specialChar) {
        switch (specialChar) {
            case 'ä':
            case 'Ä':
            case 'æ':
                return "ae";
            case 'ö':
            case 'Ö':
                return "oe";
            case 'Ü':
            case 'ü':
                return "ue";
            case 'ß':
                return "ss";
            case 'À':
            case 'Á':
            case 'Â':
            case 'Ã':
            case 'Å':
            case 'Ā':
            case 'Ą':
            case 'Ă':
            case 'à':
            case 'á':
            case 'â':
            case 'ã':
            case 'å':
            case 'ā':
            case 'ą':
            case 'ă':
                return "a";
            case 'ç':
            case 'ć':
            case 'č':
            case 'ĉ':
            case 'ċ':
            case 'Ç':
            case 'Ć':
            case 'Č':
            case 'Ĉ':
            case 'Ċ':
                return "c";
            case 'ď':
            case 'đ':
            case 'ð':
            case 'Ď':
            case 'Đ':
            case 'Ð':
                return "d";
            case 'È':
            case 'É':
            case 'Ê':
            case 'Ë':
            case 'Ē':
            case 'Ę':
            case 'Ě':
            case 'Ĕ':
            case 'Ė':
            case 'è':
            case 'é':
            case 'ê':
            case 'ë':
            case 'ē':
            case 'ę':
            case 'ě':
            case 'ĕ':
            case 'ė':
                return "e";
            case 'ƒ':
            case 'ſ':
                return "f";
            case 'Ġ':
            case 'Ģ':
            case 'Ĝ':
            case 'Ğ':
            case 'ĝ':
            case 'ğ':
            case 'ġ':
            case 'ģ':
                return "g";
            case 'Ĥ':
            case 'Ħ':
            case 'ĥ':
            case 'ħ':
                return "h";
            case 'Ì':
            case 'Í':
            case 'Î':
            case 'Ï':
            case 'Ī':
            case 'Ĩ':
            case 'Ĭ':
            case 'Į':
            case 'İ':
            case 'ì':
            case 'í':
            case 'î':
            case 'ï':
            case 'ī':
            case 'ĩ':
            case 'ĭ':
            case 'į':
            case 'ı':
                return "i";
            case 'ĳ':
                return "ij";
            case 'Ĵ':
            case 'ĵ':
                return "j";
            case 'Ķ':
            case 'ķ':
            case 'ĸ':
                return "k";
            case 'ł':
            case 'ľ':
            case 'ĺ':
            case 'ļ':
            case 'ŀ':
            case 'Ł':
            case 'Ľ':
            case 'Ĺ':
            case 'Ļ':
            case 'Ŀ':
                return "l";
            case 'Ñ':
            case 'Ń':
            case 'Ň':
            case 'Ņ':
            case 'Ŋ':
            case 'ñ':
            case 'ń':
            case 'ň':
            case 'ņ':
            case 'ŉ':
            case 'ŋ':
                return "n";
            case 'ò':
            case 'ó':
            case 'ô':
            case 'õ':
            case 'ø':
            case 'ō':
            case 'ő':
            case 'ŏ':
            case 'œ':
            case 'Ò':
            case 'Ó':
            case 'Ô':
            case 'Õ':
            case 'Ø':
            case 'Ō':
            case 'Ő':
            case 'Ŏ':
                return "o";
            case 'Þ':
            case 'þ':
                return "p";
            case 'ŕ':
            case 'ř':
            case 'ŗ':
            case 'Ŕ':
            case 'Ř':
            case 'Ŗ':
                return "r";
            case 'Ś':
            case 'Š':
            case 'Ş':
            case 'Ŝ':
            case 'Ș':
            case 'ś':
            case 'š':
            case 'ş':
            case 'ŝ':
            case 'ș':
                return "s";

            case 'ť':
            case 'ţ':
            case 'ŧ':
            case 'ț':
                return "t";
            case 'Ù':
            case 'Ú':
            case 'Û':
            case 'Ū':
            case 'Ů':
            case 'Ű':
            case 'Ŭ':
            case 'Ũ':
            case 'Ų':
            case 'ù':
            case 'ú':
            case 'û':
            case 'ū':
            case 'ů':
            case 'ű':
            case 'ŭ':
            case 'ũ':
            case 'ų':
                return "u";
            case 'Ŵ':
            case 'ŵ':
                return "w";
            case 'Ý':
            case 'Ŷ':
            case 'Ÿ':
            case 'ý':
            case 'ÿ':
            case 'ŷ':
                return "y";
            case 'Ź':
            case 'Ž':
            case 'Ż':
            case 'ž':
            case 'ż':
            case 'ź':
                return "z";
            default:
                return "-";
        }
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

        string = string.replaceAll("([a-z])'s([^a-z])", "$1s$2"); // WTF ?
        string = removeSpecialCharactersAndConvertToLowercase(string);

        string = string.replaceAll("-+$", "").replaceAll("^-+", "");
        string = removeLeadingAndTrailingHyphens(string);

        return string;
    }

    private static String removeLeadingAndTrailingHyphens(String string) {
        int leadingHyphens = numberOfLeadingHyphens(string);
        if (leadingHyphens > 0) {
            string = string.substring(leadingHyphens);
        }
        int trailingHyphens = numberOfTrailingHyphens(string);
        if (trailingHyphens > 0) {
            string = string.substring(0, string.length() - trailingHyphens);
        }
        return string;
    }

    private static int numberOfTrailingHyphens(String input) {
        int len = input.length();
        int idx = 0;
        while (input.charAt(len - idx - 1) == '-') {
            ++idx;
        }
        return idx;
    }

    private static int numberOfLeadingHyphens(String input) {
        int idx = 0;
        while (input.charAt(idx) == '-') {
            ++idx;
        }
        return idx;
    }
}