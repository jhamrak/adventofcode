package aoc25.day6;

import common.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> data = Helper.readLines(Main.class, Helper.INPUT);
//        Long result = part1(data);
        Long result = part2(data);
        System.out.println(result);
    }

    private static Long part1(List<String> data) {
        Long result = 0L;

        Long[][] numbers = new Long[(int) Arrays.stream(data.get(1).split(" ")).filter(s -> !s.isEmpty()).count()][data.size() - 1];
        for (int i = 0; i < data.size() - 1; i++) {
            String line = data.get(i);
            String[] split = Arrays.stream(line.split(" ")).filter(s -> !s.isEmpty()).toArray(String[]::new);
            for (int j = 0; j < split.length; j++) {
                numbers[j][i] = Long.parseLong(split[j]);
            }
            System.out.println(Arrays.toString(split));
        }
        System.out.println(Arrays.deepToString(numbers));

        String[] operations = Arrays.stream(data.get(data.size() - 1).split(" ")).filter(s -> !s.isEmpty()).toArray(String[]::new);
        System.out.println(Arrays.toString(operations));
        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            Long partResult = operation.equals("+") ? 0L : 1L;
            for (Long number : numbers[i]) {
                if (operation.equals("+")) {
                    partResult = partResult + number;
                } else {
                    partResult = partResult * number;
                }
            }
            result += partResult;
            System.out.println(partResult);
        }
        return result;
    }

    private static Long part2(List<String> data) {
        Long result = 0L;

//        Long[][] numbers = new Long[(int) Arrays.stream(data.get(1).split(" ")).filter(s -> !s.isEmpty()).count()][data.size() - 1];
//        int numberCount = 0;
//        int whitespaceBefore = 0;

        // i. oszlop
        char lastOperation = ' ';
        List<Long> currentNumbers = new ArrayList<>();
        int maxSize = 0;
        for (int k = 0; k < data.size(); k++) {
            if (data.get(k).length() > maxSize) {
                maxSize = data.get(k).length();
            }
        }
        for (int i = 0; i < maxSize; i++) {
            StringBuilder numberString = new StringBuilder();
            // j. sor
            for (int j = 0; j < data.size() - 1; j++) {
                if ((data.get(j).length() > i && data.get(j).charAt(i) != ' ')) {
                    numberString.append(data.get(j).charAt(i));
                }
            }

            char operation =  lastOperation;
            if(data.getLast().length() > i) {
                if (data.getLast().charAt(i) != ' ') {
                    lastOperation = data.getLast().charAt(i);
                }
                operation =  data.getLast().charAt(i) == ' ' ? lastOperation : data.getLast().charAt(i);
            }

            if (numberString.toString().trim().isEmpty()) {
                System.out.println("All whitespace at index: " + i);
                if (operation == '+') {
                    Long partResult = currentNumbers.stream().reduce(0L, Long::sum);
                    System.out.println("part result: " + partResult);
                    result += partResult;
                } else {
                    Long partResult = currentNumbers.stream().reduce(1L, (a, b) -> a * b);
                    System.out.println("part result: " + partResult);
                    result += partResult;
                }
                currentNumbers = new ArrayList<>();
            } else {
                System.out.println("Number at index " + i + ": " + numberString.toString().trim());
                currentNumbers.add(Long.parseLong(numberString.toString().replaceAll(" ", "")));
            }
            if(i == maxSize -1) {
                if (operation == '+') {
                    Long partResult = currentNumbers.stream().reduce(0L, Long::sum);
                    System.out.println("part result: " + partResult);
                    result += partResult;
                } else {
                    Long partResult = currentNumbers.stream().reduce(1L, (a, b) -> a * b);
                    System.out.println("part result: " + partResult);
                    result += partResult;
                }
            }
        }


//        Long[][] numbers = new Long[(int) Arrays.stream(data.get(1).split(" ")).filter(s -> !s.isEmpty()).count()][data.size() - 1];
//        for (int i = 0; i < data.size() - 1; i++) {
//            String line = data.get(i);
//            String[] split = Arrays.stream(line.split(" ")).filter(s -> !s.isEmpty()).toArray(String[]::new);
//            for (int j = 0; j < split.length; j++) {
//                numbers[j][i] = Long.parseLong(split[j]);
//            }
//        }
//        System.out.println(Arrays.deepToString(numbers));
//
//        String[] operations = Arrays.stream(data.get(data.size() - 1).split(" ")).filter(s -> !s.isEmpty()).toArray(String[]::new);
//        System.out.println(Arrays.toString(operations));
//        for (int i = 0; i < operations.length; i++) {
//            String operation = operations[i];
//            Long partResult = operation.equals("+") ? 0L : 1L;
//            String actNumber = "";
//            Long modulo = 10L;
//            while(true) {
//                for (Long number : numbers[i]) {
//                    number.
//                    actNumber += number % modulo;
//                }
//                modulo *= 10;
//            }
//            for (Long number : numbers[i]) {
//                if (operation.equals("+")) {
//                    partResult = partResult + number;
//                } else {
//                    partResult = partResult * number;
//                }
//            }
//            result += partResult;
//            System.out.println(partResult);
//        }


        return result;


    }

}
