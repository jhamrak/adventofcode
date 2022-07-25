package hu.jhamrak.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {
        List<String> report = Files.readAllLines(Paths.get("day3.txt"));
        calculateGammaAndEpsilonRates(report);
        calculateOGAndCO2Rates(report);
    }

    private static void calculateGammaAndEpsilonRates(List<String> report) {
        String gamma = "";
        String epsilon = "";
        int gammaDecimal = 0;
        int epsilonDecimal = 0;
        for (int i = 0; i < report.get(0).length(); i++) {
            int mostCommonBitCounter = 0;
            for (String binaryString : report) {
                mostCommonBitCounter += binaryString.charAt(i) == '1' ? 1 : -1;
            }
            if (mostCommonBitCounter > 0) {
                gamma = gamma.concat("1");
                epsilon = epsilon.concat("0");
            } else {
                gamma = gamma.concat("0");
                epsilon = epsilon.concat("1");

            }

            gammaDecimal = Integer.parseInt(gamma, 2);
            epsilonDecimal = Integer.parseInt(epsilon, 2);
        }
        System.out.println(gamma);
        System.out.println(epsilon);
        System.out.println(gammaDecimal);
        System.out.println(epsilonDecimal);
        System.out.println(gammaDecimal * epsilonDecimal);
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
