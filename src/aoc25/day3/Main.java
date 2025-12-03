package aoc25.day3;

import common.Helper;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> banks = Helper.readLines(Main.class, Helper.INPUT);
//        Long result = part1(banks);
        Long result = part2(banks);
        System.out.println(result);
    }

    private static Long part1(List<String> banks) {
        Long result = 0L;

        // bankok
        for (String bank : banks) {
            List<Integer> bankNumbers = getBankNumbers(bank);
            int firstMax = 0;
            int secondMax = 0;
            // bank-on belül első max
            for (int i = 0; i < bankNumbers.size() - 1; i++) {
                if (bankNumbers.get(i) > firstMax) {
                    firstMax = bankNumbers.get(i);
                    secondMax = 0;
                    // ha az első max változik, második maxot hozzá keressük
                    for (int j = i + 1; j < bankNumbers.size(); j++) {
                        if (bankNumbers.get(j) > secondMax) {
                            secondMax = bankNumbers.get(j);
                        }
                    }
                }
            }
            int joltage = Integer.parseInt(String.valueOf(firstMax) + String.valueOf(secondMax));
            System.out.println("Bank: " + bank + ", firstMax: " + firstMax + ", secondMax: " + secondMax + ", joltage: " + joltage);
            result += joltage;
        }

        return result;
    }


    private static Long part2(List<String> banks) {
        Long result = 0L;

        // bankok
        for (String bank : banks) {
            List<Integer> bankNumbers = getBankNumbers(bank);
            int[] joltages = new int[12];
            int[] joltagePositions = new int[12];
            // fix 12 joltage
            for (int i = 0; i < 12; i++) {
                int previousPosition = i == 0 ? -1 : joltagePositions[i - 1];
                // előző joltage pozíciótól nézzük addig, hgy még a következő joltage-k is beleférjenek
                for (int j = previousPosition + 1; j < bankNumbers.size() - (11 - i); j++) {
                    if (bankNumbers.get(j) > joltages[i]) {
                        joltages[i] = bankNumbers.get(j);
                        joltagePositions[i] = j;
                    }
                }

            }
            StringBuilder sb = new StringBuilder();
            for (int joltage : joltages) {
                sb.append(joltage);
            }
            Long joltage = Long.parseLong(sb.toString());
            result += joltage;
            System.out.println("Bank: " + bank + ", joltage: " + joltage);
        }


        return result;
    }

    private static List<Integer> getBankNumbers(String bank) {
        List<Integer> numbers = new ArrayList<>();
        for (char c : bank.toCharArray()) {
            numbers.add(Integer.parseInt(String.valueOf(c)));
        }
        return numbers;
    }

}
