package hu.jhamrak.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {
        List<String> rawPositions = Files.readAllLines(Paths.get("day7.txt"));
        List<String> stringPositions = List.of(rawPositions.get(0).split(","));
        List<Integer> positions = stringPositions.stream().map(Integer::parseInt).collect(Collectors.toList());
        int minCost = calculateMinCost(positions);
        System.out.println(minCost);
    }

    private static int calculateMinCost(List<Integer> positions) {
        int max = Collections.max(positions);
        int minCost = -1;
        for (int i = 0; i < max; i++) {
            int cost = 0;
            for (Integer position : positions) {
                int diff = Math.abs(i - position);
                    cost += (diff + 1) * diff / 2;

            }
            if (cost < minCost || minCost == -1) {
                minCost = cost;
            }

        }
        return minCost;
    }

}
