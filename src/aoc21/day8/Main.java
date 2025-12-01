package aoc21.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {
        List<String> rawEntries = Files.readAllLines(Paths.get("day8-2.txt"));

        List<String> rawCodeEntries = rawEntries.stream().map(entry -> entry.substring(entry.indexOf("|") + 2)).collect(Collectors.toList());
        List<String> entries = new ArrayList<>();
        rawCodeEntries.forEach(entry -> entries.addAll(List.of(entry.split(" "))));

        List<String> rawSignalEntries = rawEntries.stream().map(entry -> entry.substring(0, entry.indexOf("|") - 1)).collect(Collectors.toList());
        List<String> signalEntries = new ArrayList<>();
        rawSignalEntries.forEach(entry -> signalEntries.addAll(List.of(entry.split(" "))));

        long entryCount = countRelevantEntries(entries);
        System.out.println(entryCount);
    }

    private static long countRelevantEntries(List<String> entries) {
        return entries.stream().filter(entry -> entry.length() <= 4 || entry.length() == 7).count();
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
//1100
//0
//1072
//2
//1034
//4
//1
//6

}
