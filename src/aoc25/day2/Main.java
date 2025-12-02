package aoc25.day2;

import common.Helper;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> data = Helper.readLines(Main.class, "input.txt");
        String[] intervals = data.get(0).split(",");
        List<Long> numbers = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            Long intervalStart = Long.parseLong(intervals[i].split("-")[0]);
            Long intervalEnd = Long.parseLong(intervals[i].split("-")[1]);
            for (Long j = intervalStart; j <= intervalEnd; j++) {
                numbers.add(j);
            }
        }
        System.out.println(data);
        System.out.println(numbers);
//        Long result = part1(numbers);
        Long result = part2(numbers);
        System.out.println(result);
    }

    private static Long part1(List<Long> numbers) {
        Long sum = 0L;
        for (int i = 0; i < numbers.size(); i++) {
            String stringValue = numbers.get(i).toString();
            String firstHalf = stringValue.substring(0, stringValue.length() / 2);
            String secondHalf = stringValue.substring(stringValue.length() / 2);
            if (firstHalf.equals(secondHalf)) {
                sum += numbers.get(i);
                System.out.println(numbers.get(i) + " added to sum");
            }
        }
        return sum;
    }

    private static Long part2(List<Long> numbers) {

        Long sum = 0L;
        // számok
        for (int i = 0; i < numbers.size(); i++) {
            String stringValue = numbers.get(i).toString();
            // adott számon belül substringek
            for (int j = 1; j < stringValue.length(); j++) {
                String part = stringValue.substring(0, j);
                boolean match = true;
                int index = part.length();
                // substring ismétlődés
                while (index < stringValue.length()) {
                    String nextPart = stringValue.substring(index, Math.min(index + part.length(), stringValue.length()));
                    if (!part.equals(nextPart)) {
                        match = false;
                        break;
                    }
                    index += part.length();
                }
                if (match) {
                    sum += numbers.get(i);
                    System.out.println(stringValue + " matches " + part);
                    System.out.println(numbers.get(i) + " added to sum");
                    break;
                }
            }
        }
        return sum;
    }

}
