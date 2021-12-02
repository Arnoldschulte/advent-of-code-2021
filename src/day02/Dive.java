package day02;

import util.ReaderUtil;
import util.StringUtil;

import java.util.List;

public class Dive {

    private static final String INPUT_PATH = "src/day02/input.txt";

    public static void main(String[] args) {
//      Part 01
        final List<String> courseLines = ReaderUtil.getLinesFromTextFile(INPUT_PATH);

        int result = getMultiplicationDepthAndHorizontalPosition(courseLines, false);
        System.out.println("Multiplication of depth and horizontal position: " + result);

//      Part 02
        result = getMultiplicationDepthAndHorizontalPosition(courseLines, true);
        System.out.println("Multiplication of depth and horizontal position: " + result);
    }

    private static int getMultiplicationDepthAndHorizontalPosition(List<String> courseLines, boolean withAim) {
        int depth = 0;
        int horizontalPosition = 0;
        int aim = 0;

        for (String courseLine : courseLines) {
            int number = StringUtil.getIntegerFromString(courseLine);

            if (courseLine.contains("forward")) {
                horizontalPosition += number;

                if (withAim) {
                    depth += (number * aim);
                }

            } else if (courseLine.contains("up")) {

                if (withAim) {
                    aim -= number;
                } else {
                    depth -= number;
                }

            } else if (courseLine.contains("down")) {

                if (withAim) {
                    aim += number;
                } else {
                    depth += number;
                }
            }
        }
        return depth * horizontalPosition;
    }

}
