package day03;

import util.ReaderUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryDiagnostic {
    public static void main(String[] args) {
//      Part 01
        final List<String> lines = ReaderUtil.getLinesFromTextFile("src/day03/input.txt");

        final StringBuilder gammaRateBinary = new StringBuilder();
        final StringBuilder epsilonRateBinary = new StringBuilder();

        for (int i = 0; i < lines.get(0).length(); i++) {
            gammaRateBinary.append(getMostCommonBinaryNumber(lines, i));
            epsilonRateBinary.append(getLeastCommonBinaryNumber(lines, i));
        }
        final int gammaRate = getDecimalFromBinary(gammaRateBinary.toString());
        final int epsilonRate = getDecimalFromBinary(epsilonRateBinary.toString());

        System.out.println("The power consumption of the submarine is: " + gammaRate * epsilonRate);

//      Part 02
        final String oxygenGeneratorRatingBinary = getOxygenGeneratorRating(lines);
        final String co2ScrubberRatingBinary = getC02ScrubberRating(lines);

        final int oxygenGeneratorRating = getDecimalFromBinary(oxygenGeneratorRatingBinary);
        final int co2ScrubberRating = getDecimalFromBinary(co2ScrubberRatingBinary);

        System.out.println("The life support rating of the submarine is: " + oxygenGeneratorRating * co2ScrubberRating);
    }

    private static int getDecimalFromBinary(String binary) {
        return Integer.parseInt(binary, 2);
    }

    private static String getMostCommonBinaryNumber(List<String> lines, int index) {
        final List<Character> chars = new ArrayList<>();

        for (String line : lines) {
            chars.add(line.charAt(index));
        }

        return Collections.frequency(chars, '1') >= Collections.frequency(chars, '0') ? "1" : "0";
    }

    private static String getLeastCommonBinaryNumber(List<String> lines, int index) {
        final List<Character> chars = new ArrayList<>();

        for (String line : lines) {
            chars.add(line.charAt(index));
        }

        return Collections.frequency(chars, '1') >= Collections.frequency(chars, '0') ? "0" : "1";
    }

    private static String getOxygenGeneratorRating(List<String> lines) {
        final List<String> oxygenGeneratorRatingList = new ArrayList<>(lines);

        for (int i = 0; i < lines.get(0).length(); i++) {
            final String number = getMostCommonBinaryNumber(oxygenGeneratorRatingList, i);

            int index = i;
            oxygenGeneratorRatingList.removeIf(s -> !String.valueOf(s.charAt(index)).equals(number) && oxygenGeneratorRatingList.size() > 1);
        }

        return oxygenGeneratorRatingList.get(0);
    }

    private static String getC02ScrubberRating(List<String> lines) {
        final List<String> co2ScrubberRatingList = new ArrayList<>(lines);

        for (int i = 0; i < lines.get(0).length(); i++) {
            final String number = getLeastCommonBinaryNumber(co2ScrubberRatingList, i);

            int index = i;
            co2ScrubberRatingList.removeIf(s -> !String.valueOf(s.charAt(index)).equals(number) && co2ScrubberRatingList.size() > 1);
        }

        return co2ScrubberRatingList.get(0);
    }
}
