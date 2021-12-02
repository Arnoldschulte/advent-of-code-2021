package day02;

import util.ReaderUtil;
import util.StringUtil;

import java.util.List;

public class Dive {

    private static final String INPUT_PATH = "src/day02/input.txt";

    public static void main(String[] args) {
//      Part 01
        final List<String> courseLines = ReaderUtil.getLinesFromTextFile(INPUT_PATH);

        int depth = 0;
        int horizontalPosition = 0;

        for (String courseLine : courseLines) {
            int number = StringUtil.getIntegerFromString(courseLine);

            if (courseLine.contains("forward")) {
                horizontalPosition += number;
            } else if (courseLine.contains("up")) {
                depth -= number;
            } else if (courseLine.contains("down")) {
                depth += number;
            }
        }

        System.out.println("Multiplication of depth and horizontal position: " + depth * horizontalPosition);

//      Part 02
        depth = 0;
        horizontalPosition = 0;
        int aim = 0;

        for (String courseLine : courseLines) {
            int number = StringUtil.getIntegerFromString(courseLine);

            if (courseLine.contains("forward")) {
                horizontalPosition += number;
                depth += (number * aim);
            } else if (courseLine.contains("up")) {
                aim -= number;
            } else if (courseLine.contains("down")) {
                aim += number;
            }
        }

        System.out.println("Multiplication of depth and horizontal position: " + depth * horizontalPosition);
    }
}
