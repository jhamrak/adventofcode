package aoc21.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) throws IOException {
        List<Board> boards = new ArrayList<>();
        List<String> numbers = Arrays.asList(Files.lines(Paths.get("day4.txt")).findFirst().get().split(","));
        try (Scanner s = new Scanner(new BufferedReader(new FileReader("day4.txt")))) {
            s.next();
            while (s.hasNext()) {
                boards.add(new Board(new ArrayList<>()));
                for (int i = 0; i < 5; i++) {
                    boards.get(boards.size() - 1).getNumbers().add(i, new ArrayList<>());
                    for (int j = 0; j < 5; j++) {
                        boards.get(boards.size() - 1).getNumbers().get(i).add(j, new Number(s.nextInt(), false));
                    }
                }
            }
        }

        calculateScore(boards, numbers.stream().map(Integer::parseInt).collect(Collectors.toList()));
    }

    private static void calculateScore(List<Board> boards, List<Integer> numbers) {
        List<Board> remaining = new ArrayList<>();
        int luckyNumber = 0;
        int i = 0;
        do {
            luckyNumber = numbers.get(i);
            mark(boards, luckyNumber);
            remaining = checkGameEnd(boards);
            i++;
        } while (i < 5 || remaining.size() != 1);
        int score = countScore(remaining.get(0)) * luckyNumber;
        System.out.println(score);
        System.out.println(luckyNumber);
        System.out.println(remaining.get(0).getNumbers());
    }

    private static int countScore(Board winner) {
        AtomicInteger score = new AtomicInteger();
        winner.getNumbers().forEach(row -> row.forEach(number -> { if(!number.isMarked()) { score.addAndGet(number.getValue());}}));
        return score.get();
    }

    private static List<Board> checkGameEnd(List<Board> boards) {
        List<Board> remaining = new ArrayList<>();
        for (Board board :
                boards) {
            boolean won = false;
            for (int i = 0; i < 5; i++) {
                boolean markedRow = true;
                boolean markedColumn = true;
                for (int j = 0; j < 5; j++) {
                    if (!board.getNumbers().get(i).get(j).isMarked()) {
                        markedRow = false;
                    }
                    if (!board.getNumbers().get(j).get(i).isMarked()) {
                        markedColumn = false;
                    }
                }
                if(markedColumn || markedRow) {
                    won = true;
                    break;
                }
            }
            if(!won) {
                remaining.add(board);
            }

    }
        return remaining;
    }

    private static void mark(List<Board> boards, Integer luckyNumber) {
        for (Board board :
                boards) {
            board.getNumbers().forEach(row -> row.forEach(number -> {
                if (number.getValue() == luckyNumber) number.setMarked(true);
            }));
        }
    }

}
