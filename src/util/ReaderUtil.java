package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReaderUtil {

    public ReaderUtil() {
        // Encapsulation
    }

    public static List<Integer> getNumbersFromTextFile(String path) {
        return getLinesFromTextFile(path)
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<String> getLinesFromTextFile(String path) {
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("The current error is occurred: " + e.getMessage());

            return new ArrayList<>();
        }
    }
}
