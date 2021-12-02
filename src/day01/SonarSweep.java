package day01;

import java.util.ArrayList;
import java.util.List;

public class SonarSweep {
    public static void main(String[] args) {
//      Part 01
        final List<Integer> numbers = ReaderUtil.getLinesFromTextFile("src/day01/input.txt");
        System.out.println("Number of depth measurement increments: " + getNumberOfIncrements(numbers));

//      Part 02
        final List<Integer> sumOfNumbers = getSumOfNumbers(numbers);
        System.out.println("Number of depth increments with three-measurement sliding window: " + getNumberOfIncrements(sumOfNumbers));
    }

    private static int getNumberOfIncrements(List<Integer> numbers) {
        int increments = 0;
        int previousNumber = 0;
        int currentNumber;

        for (int number : numbers) {
            currentNumber = number;
            if (previousNumber != 0 && currentNumber > previousNumber) {
                increments++;
            }
            previousNumber = currentNumber;
        }
        return increments;
    }

    private static List<Integer> getSumOfNumbers(List<Integer> numbers) {
        final List<Integer> listOfSums = new ArrayList<>();
        int numberOne;
        int numberTwo;
        int numberThree;

        for (int i = 0; i < numbers.size() - 2; i++) {
            numberOne = numbers.get(i);
            numberTwo = numbers.get(i + 1);
            numberThree = numbers.get(i + 2);

            listOfSums.add(numberOne + numberTwo + numberThree);
        }
        return listOfSums;
    }
}
