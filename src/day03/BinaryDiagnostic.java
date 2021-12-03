package day03;

import util.ReaderUtil;

import java.util.ArrayList;
import java.util.List;

import static util.BinaryUtil.*;

public class BinaryDiagnostic {

    private static final String INPUT_PATH = "src/day03/input.txt";

    public static void main(String[] args) {
//      Part 01
        final List<String> lines = ReaderUtil.getLinesFromTextFile(INPUT_PATH);

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
        final int oxygenGeneratorRating = getOxygenGeneratorRatingBinary(lines);
        final int co2ScrubberRating = getC02ScrubberRatingBinary(lines);

        System.out.println("The life support rating of the submarine is: " + oxygenGeneratorRating * co2ScrubberRating);
    }

    private static int getOxygenGeneratorRatingBinary(List<String> lines) {
        return getRatingBinary(lines, true);
    }

    private static int getC02ScrubberRatingBinary(List<String> lines) {
        return getRatingBinary(lines, false);
    }

    private static int getRatingBinary(List<String> lines, boolean mostCommonBinary) {
        final List<String> filteredList = new ArrayList<>(lines);

        for (int i = 0; i < lines.get(0).length(); i++) {
            final String number = mostCommonBinary ? getMostCommonBinaryNumber(filteredList, i) : getLeastCommonBinaryNumber(filteredList, i);

            int index = i;
            filteredList.removeIf(s -> !String.valueOf(s.charAt(index)).equals(number) && filteredList.size() > 1);
        }

        return getDecimalFromBinary(filteredList.get(0));
    }
}
