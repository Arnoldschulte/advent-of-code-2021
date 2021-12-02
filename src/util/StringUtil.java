package util;

import static java.lang.Integer.parseInt;

public class StringUtil {

    public StringUtil() {
        // Encapsulation
    }

    public static int getIntegerFromString(String text) {
        return parseInt(text.replaceAll("[^0-9]", ""));
    }

}
