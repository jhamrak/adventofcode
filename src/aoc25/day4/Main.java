package aoc25.day4;

import common.Helper;

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

        int[][] matrix = new int[data.size()][data.get(0).length()];
        for (int i = 0; i < data.size(); i++) {
            String row = data.get(i);
            for (int j = 0; j < row.length(); j++) {
                matrix[i][j] = row.charAt(j) == '@' ? 1 : 0;
            }
        }
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n"));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    int sum = getSumOfAdjacent(matrix, i, j);
                    if (sum < 4) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    private static Long part2(List<String> data) {
        Long result = 0L;

        int[][] matrix = new int[data.size()][data.get(0).length()];
        for (int i = 0; i < data.size(); i++) {
            String row = data.get(i);
            for (int j = 0; j < row.length(); j++) {
                matrix[i][j] = row.charAt(j) == '@' ? 1 : 0;
            }
        }
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n"));

        boolean change = true;
        while (change) {
            int[][] matrixAfter = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrixAfter[i][j] = matrix[i][j];
                    if (matrix[i][j] == 1) {
                        int sum = getSumOfAdjacent(matrix, i, j);
                        if (sum < 4) {
                            result++;
                            matrixAfter[i][j] = 0;
                        }
                    }
                }
            }
            change = !Arrays.deepEquals(matrix, matrixAfter);
            matrix = matrixAfter;
        }

        return result;
    }

    private static int getSumOfAdjacent(int[][] matrix, int i, int j) {
        int sum = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                if (x == i && y == j) {
                    continue;
                }
                if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[i].length) {
                    sum += matrix[x][y];
                }
            }
        }
        return sum;
    }

}
