package hu.jhamrak.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {
        try (Scanner s = new Scanner(new BufferedReader(new FileReader("day6.txt")))) {

            s.useDelimiter(",");
            List<Integer> fishList = new ArrayList<>();
            while (s.hasNext()) {
                fishList.add(Integer.parseInt(s.next().trim()));
            }
            calculateFishListAfter80Days(fishList);
        }
    }

    private static void calculateFishListAfter80Days(List<Integer> fishList) {

        Map<Integer, Long> fishMap = fishList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        for(int i = 0; i < 9; i++) {
            if(!fishMap.containsKey(i)) {
                fishMap.put(i,0L);
            }
        }
        for (int i = 0; i < 256; i++) {
            Map<Integer, Long> newFishMap = new HashMap<>();
            for (int j = 0; j < 9; j++) {
                newFishMap.put(j, 0L);
            }
            for (Integer fish : fishMap.keySet()) {
                if (fish == 0) {
                    newFishMap.merge(6, fishMap.get(fish), Long::sum);
                    newFishMap.merge(8, fishMap.get(fish), Long::sum);
                } else {
                    newFishMap.merge(fish - 1, fishMap.get(fish), Long::sum);
                }
            }
            fishMap = newFishMap;
        }

        System.out.println(fishMap.values().stream().mapToLong(l -> l).sum());
    }


    private static void calculateOGAndCO2Rates(List<String> report) {
        int ogDecimal = calculateOgDecimal(report);
        int co2Decimal = calculateCo2Decimal(report);

        System.out.println(ogDecimal);
        System.out.println(co2Decimal);
        System.out.println(ogDecimal * co2Decimal);
    }

    private static int calculateOgDecimal(List<String> report) {
        int i = 0;
        do {
            int mostCommonBitCounter = 0;
            for (String binaryString : report) {
                mostCommonBitCounter += binaryString.charAt(i) == '1' ? 1 : -1;
            }
            int finalI = i;
            if (mostCommonBitCounter >= 0) {
                report = report.stream().filter(e -> e.charAt(finalI) == '1').collect(Collectors.toList());
            } else {
                report = report.stream().filter(e -> e.charAt(finalI) == '0').collect(Collectors.toList());

            }

            i++;
        } while (report.size() != 1);
        return Integer.parseInt(report.get(0), 2);
    }

    private static int calculateCo2Decimal(List<String> report) {
        int i = 0;
        do {
            int mostCommonBitCounter = 0;
            for (String binaryString : report) {
                mostCommonBitCounter += binaryString.charAt(i) == '1' ? 1 : -1;
            }
            int finalI = i;
            if (mostCommonBitCounter >= 0) {
                report = report.stream().filter(e -> e.charAt(finalI) == '0').collect(Collectors.toList());
            } else {
                report = report.stream().filter(e -> e.charAt(finalI) == '1').collect(Collectors.toList());

            }

            i++;
        } while (report.size() != 1);
        return Integer.parseInt(report.get(0), 2);
    }

}
