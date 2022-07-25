package hu.jhamrak.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> depths = Files.readAllLines(Paths.get("day1.txt"));
        countSingleIncreases(depths);
        countWindowIncreases(depths);
    }

    private static void countSingleIncreases(List<String> depths) {
        int singleIncreaseCount = 0;
        for (int i = 0; i < depths.size() - 1; i++) {
            if ( Integer.parseInt(depths.get(i)) < Integer.parseInt(depths.get(i + 1))) {
                singleIncreaseCount++;
            }
        }
        System.out.println(singleIncreaseCount);
    }

    private static void countWindowIncreases(List<String> depths) {
        int windowIncreaseCount = 0;
        for (int i = 0; i < depths.size() - 3; i++) {
            int actSum = Integer.parseInt(depths.get(i)) + Integer.parseInt(depths.get(i + 1)) + Integer.parseInt(depths.get(i + 2));
            int nextSum = Integer.parseInt(depths.get(i + 1)) + Integer.parseInt(depths.get(i + 2)) + Integer.parseInt(depths.get(i + 3));
            if ( actSum < nextSum) {
                windowIncreaseCount++;
            }
        }
        System.out.println(windowIncreaseCount);
    }
}
