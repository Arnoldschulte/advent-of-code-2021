package day04;

public class Bingo {

    private final String[][] board;
    private final String numberDrawn;
    private final int sumUnmarked;

    public Bingo(String[][] board, String numberDrawn, int sumUnmarked) {
        this.board = board;
        this.numberDrawn = numberDrawn;
        this.sumUnmarked = sumUnmarked;
    }

    public String[][] getBoard() {
        return board;
    }

    public int getNumberDrawn() {
        return Integer.parseInt(numberDrawn);
    }

    public int getSumUnmarked() {
        return sumUnmarked;
    }
}
