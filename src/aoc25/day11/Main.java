package aoc25.day11;

import common.Helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String YOU = "you";
    private static final String SVR = "svr";
    private static final String FFT = "fft";
    private static final String DAC = "dac";
    private static final String OUT = "out";

    private static Map<String, List<String>> graph = new HashMap<>();


    public static void main(String[] args) {
        List<String> data = Helper.readLines(Main.class, Helper.INPUT);
//        Long result = part1(data);
        Long result = part2(data);
        System.out.println(result);
    }

    private static Long part1(List<String> data) {
        Long result = 0L;

        for (String line : data) {
            String[] parts = line.split(":");
            String from = parts[0];
            String[] toParts = parts[1].replaceFirst(" ", "").split(" ");
            List<String> to = List.of(toParts);
            graph.put(from, to);
        }

        result = countPaths(YOU, OUT, new HashMap<>());
        return result;
    }

    private static Long part2(List<String> data) {
        Long result = 0L;

        for (String line : data) {
            String[] parts = line.split(":");
            String from = parts[0];
            String[] toParts = parts[1].replaceFirst(" ", "").split(" ");
            List<String> to = List.of(toParts);
            graph.put(from, to);
        }

        result = countPaths(SVR, FFT, new HashMap<>()) * countPaths(FFT, DAC, new HashMap<>()) * countPaths(DAC, OUT, new HashMap<>());
        return result;
    }

    private static long countPaths(String curr, String end, Map<String, Long> cache) {
        if (curr.equals(end)) {
            return 1;
        }
        if (cache.containsKey(curr)) {
            return cache.get(curr);
        }
        long count = 0;
        List<String> neighbours = graph.getOrDefault(curr, List.of());
        for (String neighbour : neighbours) {
            count += countPaths(neighbour, end, cache);
        }
        cache.put(curr, count);
        return count;
    }

}
