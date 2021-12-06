package day04;

import util.ReaderUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GiantSquid {

    private static final String INPUT_PATH = "src/day04/input.txt";

    public static void main(String[] args) {
        final List<String> lines = ReaderUtil.getLinesFromTextFile(INPUT_PATH);
        final List<String[][]> boards = getBoards(lines);
        final String[] numbersDrawn = lines.get(0).split(",");

        System.out.println("The final score of the first bingo is: " + getFinalScoreFirstBingo(boards, numbersDrawn));
        System.out.println("The final score of the last bingo is: " + getFinalScoreLastBingo(boards, numbersDrawn));
    }

    private static int getFinalScoreLastBingo(List<String[][]> boards, String[] numbersDrawn) {
        final List<Bingo> bingoList = new ArrayList<>();
        for (String numberDrawn : numbersDrawn) {
            for (String[][] board : boards) {
                markNumberDrawn(numberDrawn, board);

                final boolean boardAlreadyHadBingoBefore = bingoList.stream()
                        .noneMatch(bingo -> Arrays.deepEquals(bingo.getBoard(), board));

                if (boardHasBingo(board) && boardAlreadyHadBingoBefore) {
                    final Bingo bingo = new Bingo(board, numberDrawn, getSumUnmarkedNumbers(board));
                    bingoList.add(bingo);
                }
            }
        }
        final Bingo lastBingo = bingoList.get(bingoList.size() - 1);
        return lastBingo.getSumUnmarked() * lastBingo.getNumberDrawn();
    }

    private static int getFinalScoreFirstBingo(List<String[][]> boards, String[] numbersDrawn) {
        boolean firstBingo = false;
        int sumUnmarkedNumbers = 0;
        int finalScore = 0;

        for (String numberDrawn : numbersDrawn) {
            for (String[][] board : boards) {
                markNumberDrawn(numberDrawn, board);

                if (boardHasBingo(board)) {
                    firstBingo = true;
                    sumUnmarkedNumbers = getSumUnmarkedNumbers(board);
                }
            }
            if (firstBingo) {
                finalScore = sumUnmarkedNumbers * Integer.parseInt(numberDrawn);
                break;
            }
        }
        return finalScore;
    }

    private static int getSumUnmarkedNumbers(String[][] board) {
        int sumUnmarkedNumbers = 0;
        for (String[] row : board) {
            for (String value : row) {
                sumUnmarkedNumbers += !value.equals("X") ? Integer.parseInt(value) : 0;
            }
        }
        return sumUnmarkedNumbers;
    }

    private static void markNumberDrawn(String numberDrawn, String[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board[row][column].equals(numberDrawn)) {
                    board[row][column] = "X";
                }
            }
        }
    }

    private static boolean boardHasBingo(String[][] board) {
        return rowHasBingo(board, 0) ||
                rowHasBingo(board, 1) ||
                rowHasBingo(board, 2) ||
                rowHasBingo(board, 3) ||
                rowHasBingo(board, 4) ||
                columnHasBingo(board, 0) ||
                columnHasBingo(board, 1) ||
                columnHasBingo(board, 2) ||
                columnHasBingo(board, 3) ||
                columnHasBingo(board, 4);
    }

    private static boolean rowHasBingo(String[][] board, int rowIndex) {
        return board[rowIndex][0].equals("X") &&
                board[rowIndex][1].equals("X") &&
                board[rowIndex][2].equals("X") &&
                board[rowIndex][3].equals("X") &&
                board[rowIndex][4].equals("X");
    }

    private static boolean columnHasBingo(String[][] board, int columnIndex) {
        return board[0][columnIndex].equals("X") &&
                board[1][columnIndex].equals("X") &&
                board[2][columnIndex].equals("X") &&
                board[3][columnIndex].equals("X") &&
                board[4][columnIndex].equals("X");
    }

    private static List<String[][]> getBoards(List<String> lines) {
        final List<String[][]> boards = new ArrayList<>();
        String[][] board = new String[5][5];

        int counter = 1;
        for (String line : lines) {
            if (counter == 6) {
                boards.add(board);

                board = new String[5][5];
                counter = 1;
            }

            if (line.contains(" ")) {
                final List<String> numbers = Arrays.stream(line.split(" "))
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
                for (int i = 0; i < numbers.size(); i++) {
                    board[counter - 1][i] = numbers.get(i);
                }
                counter++;
            }
        }
        return boards;
    }
}
