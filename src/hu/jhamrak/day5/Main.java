package hu.jhamrak.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException {
        List<String> rawBoard = Files.readAllLines(Paths.get("day5.txt"));
        Map<Coordinate, Integer> board = transformToBoard(rawBoard);

        System.out.println(board.values().stream().filter(val -> val > 1).count());
    }

    private static Map<Coordinate, Integer> transformToBoard(List<String> rawBoard) {
        Map<Coordinate, Integer> board = new HashMap<>();
        for (String row : rawBoard) {
            String from = row.substring(0, row.indexOf(" -> "));
            String to = row.substring(row.indexOf(" -> ") + 4);
            int fromX = Integer.parseInt(from.substring(0, from.indexOf(",")));
            int fromY = Integer.parseInt(from.substring(from.indexOf(",") + 1));
            int toX = Integer.parseInt(to.substring(0, to.indexOf(",")));
            int toY = Integer.parseInt(to.substring(to.indexOf(",") + 1));
            Coordinate fromCoordinate = new Coordinate(fromX, fromY);
            Coordinate toCoordinate = new Coordinate(toX, toY);
            addVerticalLine(board, fromCoordinate, toCoordinate);
            addHorizontalLine(board, fromCoordinate, toCoordinate);
            addDiagonalLine(board, fromCoordinate, toCoordinate);

        }
        return board;
    }

    private static void addDiagonalLine(Map<Coordinate, Integer> board, Coordinate fromCoordinate, Coordinate toCoordinate) {
        if (fromCoordinate.getX() != toCoordinate.getX() && fromCoordinate.getY() != toCoordinate.getY()) {
            int diff = Math.abs(toCoordinate.getY() - fromCoordinate.getY());
            int xSign = toCoordinate.getX() > fromCoordinate.getX() ? 1 : -1;
            int ySign = toCoordinate.getY() > fromCoordinate.getY() ? 1 : -1;
            for (int i = 0; i <= diff; i++) {
                board.merge(new Coordinate(fromCoordinate.getX() + (i * xSign), fromCoordinate.getY() + (i * ySign)), 1, Integer::sum);
            }
        }
    }

    private static void addVerticalLine(Map<Coordinate, Integer> board, Coordinate fromCoordinate, Coordinate toCoordinate) {
        if (fromCoordinate.getX() == toCoordinate.getX() && fromCoordinate.getY() != toCoordinate.getY()) {
            for (int i = Math.min(toCoordinate.getY(), fromCoordinate.getY()); i <= Math.max(toCoordinate.getY(), fromCoordinate.getY()); i++) {
                board.merge(new Coordinate(fromCoordinate.getX(), i), 1, Integer::sum);
            }
        }
    }

    private static void addHorizontalLine(Map<Coordinate, Integer> board, Coordinate fromCoordinate, Coordinate toCoordinate) {
        if (fromCoordinate.getX() != toCoordinate.getX() && fromCoordinate.getY() == toCoordinate.getY()) {
            for (int i = Math.min(toCoordinate.getX(), fromCoordinate.getX()); i <= Math.max(toCoordinate.getX(), fromCoordinate.getX()); i++) {
                board.merge(new Coordinate(i, fromCoordinate.getY()), 1, Integer::sum);
            }
        }
    }


}
