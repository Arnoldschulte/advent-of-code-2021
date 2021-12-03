package util;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.frequency;

public class BinaryUtil {

    public BinaryUtil() {
        // Encapsulation
    }

    public static int getDecimalFromBinary(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static String getMostCommonBinaryNumber(List<String> lines, int index) {
        final List<Character> chars = getCharsFromIndex(lines, index);

        return frequency(chars, '1') >= frequency(chars, '0') ? "1" : "0";
    }

    public static String getLeastCommonBinaryNumber(List<String> lines, int index) {
        final List<Character> chars = getCharsFromIndex(lines, index);

        return frequency(chars, '1') >= frequency(chars, '0') ? "0" : "1";
    }

    private static List<Character> getCharsFromIndex(List<String> lines, int index) {
        return lines.stream()
                .map(line -> line.charAt(index))
                .collect(Collectors.toList());
    }
}
