package aoc25.day9;

import common.Helper;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        List<String> data = Helper.readLines(Main.class, Helper.INPUT);
//        Long result = part1(data);
        Long result = part2(data);
        System.out.println(result);
    }

    private static Long part1(List<String> data) {
        Long result = 0L;
        List<Coordinate> coordinates = new ArrayList<>();
        for (String line : data) {
            String[] parts = line.split(",");
            coordinates.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate curr = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                Coordinate next = coordinates.get(j);

                int maxX = Math.max(curr.getX(), next.getX());
                int maxY = Math.max(curr.getY(), next.getY());
                int minX = Math.min(curr.getX(), next.getX());
                int minY = Math.min(curr.getY(), next.getY());
                Long rectangle = (long) (maxX - minX + 1) * (long) (maxY - minY + 1);
                if (rectangle > result) {
                    result = rectangle;
                    System.out.println("New max rectangle: " + rectangle + " between points " + curr.getX() + "," + curr.getY() + " and " + next.getX() + "," + next.getY());
                }
            }
        }


        return result;
    }


//    private static Long part2(List<String> data) {
//        Long result = 0L;
//        List<Coordinate> redCoordinates = new ArrayList<>();
//
//        String[] firstParts = data.get(0).split(",");
//        redCoordinates.add(new Coordinate(Integer.parseInt(firstParts[0]), Integer.parseInt(firstParts[1])));
//        for (int i = 1; i < data.size(); i++) {
//
//            String[] parts = data.get(i).split(",");
//            redCoordinates.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
//        }
//
//        for (int i = 0; i < redCoordinates.size() - 2; i++) {
//            Coordinate curr = redCoordinates.get(i);
//            int j = i + 1;
//            Direction direction = getDirection(curr, redCoordinates.get(j));
//            Coordinate between = redCoordinates.get(j);
//            while (getDirection(between, redCoordinates.get(j)) == direction) {
//                between = redCoordinates.get(j);
//                j++;
//
//            }
//            if(j > redCoordinates.size()) {
//                continue;
//            }
//            int k = j;
//            Coordinate finalCoord = redCoordinates.get(k);
//            direction = getDirection(between, redCoordinates.get(k));
//            while (k < redCoordinates.size() && getDirection(finalCoord, redCoordinates.get(k)) == direction) {
//                finalCoord = redCoordinates.get(k);
//                k++;
//
//            }
//
//            int maxX = Math.max(curr.getX(), finalCoord.getX());
//            int maxY = Math.max(curr.getY(), finalCoord.getY());
//            int minX = Math.min(curr.getX(), finalCoord.getX());
//            int minY = Math.min(curr.getY(), finalCoord.getY());
//            Long rectangle = (long) (maxX - minX + 1) * (long) (maxY - minY + 1);
//
//            if (rectangle > result) {
//
//                result = rectangle;
//                System.out.println("New max rectangle: " + rectangle + " between points " + curr.getX() + "," + curr.getY() + " and " + finalCoord.getX() + "," + finalCoord.getY());
//
//
//            }
//        }
//
//        return result;
//    }
//
//
//    private static Direction getDirection(Coordinate from, Coordinate to) {
//        if (from.getX() == to.getX()) {
//            if (from.getY() < to.getY()) {
//                return Direction.DOWN;
//            } else {
//                return Direction.UP;
//            }
//        } else if (from.getY() == to.getY()) {
//            if (from.getX() < to.getX()) {
//                return Direction.RIGHT;
//            } else {
//                return Direction.LEFT;
//            }
//        } else {
//            throw new IllegalArgumentException("Invalid coordinates for direction: " + from + " to " + to);
//        }
//    }


    private static Long part2(List<String> data) {
        Long result = 0L;
        List<Coordinate> redCoordinates = new ArrayList<>();
        List<Coordinate> allCoordinates = new ArrayList<>();

        String[] firstParts = data.get(0).split(",");
        redCoordinates.add(new Coordinate(Integer.parseInt(firstParts[0]), Integer.parseInt(firstParts[1])));
        Polygon polygon = new Polygon();
        for (int i = 1; i < data.size(); i++) {
            String[] parts = data.get(i).split(",");
            redCoordinates.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            int minX = Math.min(redCoordinates.get(i - 1).getX(), redCoordinates.get(i).getX());
            int maxX = Math.max(redCoordinates.get(i - 1).getX(), redCoordinates.get(i).getX());
            int minY = Math.min(redCoordinates.get(i - 1).getY(), redCoordinates.get(i).getY());
            int maxY = Math.max(redCoordinates.get(i - 1).getY(), redCoordinates.get(i).getY());

            IntStream.rangeClosed(minX, maxX).forEach(j -> allCoordinates.add(new Coordinate(j, Integer.parseInt(parts[1]))));
            IntStream.rangeClosed(minY, maxY).forEach(j -> allCoordinates.add(new Coordinate(Integer.parseInt(parts[0]), j)));

        }
//        System.out.println(allCoordinates);
        allCoordinates.forEach(c -> polygon.addPoint(c.getX(), c.getY()));
        Area area = new Area(polygon);

        for (int i = 0; i < redCoordinates.size(); i++) {
            Coordinate curr = redCoordinates.get(i);
            for (int j = i + 1; j < redCoordinates.size(); j++) {
                Coordinate next = redCoordinates.get(j);
                int maxX = Math.max(curr.getX(), next.getX());
                int maxY = Math.max(curr.getY(), next.getY());
                int minX = Math.min(curr.getX(), next.getX());
                int minY = Math.min(curr.getY(), next.getY());
                Long rectangle = (long) (maxX - minX + 1) * (long) (maxY - minY + 1);

//                 if(rectangle > result && isRedOrGreenRectangle(curr, next, allCoordinates)) {
                if (rectangle > result) {

//                                        System.out.println("New max possible: " + rectangle + " between points " + curr.getX() + "," + curr.getY() + " and " + next.getX() + "," + next.getY());

                    if (area.contains(minX, minY, maxX - minX, maxY - minY)) {
                        result = rectangle;
                        System.out.println("New max rectangle: " + rectangle + " between points " + curr.getX() + "," + curr.getY() + " and " + next.getX() + "," + next.getY());

                    }
                }
            }
        }


        return result;
    }
//    private static boolean isRedOrGreenRectangle(Coordinate curr, Coordinate next, List<Coordinate> coordinates) {
//        if (curr.getX() == next.getX() || curr.getY() == next.getY()) {
//            return true;
//        }
//
//        boolean currHasAdjacentColouredTile = false;
//        boolean nextHasAdjacentColouredTile = false;
//        boolean currAdjacentMustBeHigher = curr.getX() < next.getX();
//
//        for (Coordinate coordinate : coordinates) {
//            if (coordinate.equals(curr) || coordinate.equals(next)) {
//                continue;
//            }
//
//            if (coordinate.getY() == curr.getY()) {
//                if (currAdjacentMustBeHigher && coordinate.getX() >= next.getX()) {
//                    currHasAdjacentColouredTile = true;
////                    System.out.println("Found adjacent coloured tile for curr: " + coordinate + " for rectangle between " + curr + " and " + next);
//                }
//                if (!currAdjacentMustBeHigher && coordinate.getX() <= next.getX()) {
//                    currHasAdjacentColouredTile = true;
////                    System.out.println("Found adjacent coloured tile for curr: " + coordinate + " for rectangle between " + curr + " and " + next);
//                }
//            }
//
//
//            if (coordinate.getY() == next.getY()) {
//                if (currAdjacentMustBeHigher && coordinate.getX() <= curr.getX()) {
//                    nextHasAdjacentColouredTile = true;
////                    System.out.println("Found adjacent coloured tile for next: " + coordinate + " for rectangle between " + curr + " and " + next);
//                }
//                if (!currAdjacentMustBeHigher && coordinate.getX() >= curr.getX()) {
//                    nextHasAdjacentColouredTile = true;

    /// /                    System.out.println("Found adjacent coloured tile for next: " + coordinate + " for rectangle between " + curr + " and " + next);
//                }
//            }
//        }
//
//        return currHasAdjacentColouredTile && nextHasAdjacentColouredTile;
//
//    }
//    private static boolean isRedOrGreenRectangle(Coordinate curr, Coordinate next, List<Coordinate> coordinates) {
//
//        int maxX = Math.max(curr.getX(), next.getX());
//        int maxY = Math.max(curr.getY(), next.getY());
//        int minX = Math.min(curr.getX(), next.getX());
//        int minY = Math.min(curr.getY(), next.getY());
//        for (int i = minX; i <= maxX; i++) {
//            if (!coordinates.contains(new Coordinate(i, minY)) || !coordinates.contains(new Coordinate(i, maxY))) {
//                System.out.println("Missing coordinate at X=" + i + " for rectangle between " + curr + " and " + next);
//                return false;
//            }
//        }
//
//        for (int i = minY; i <= maxY; i++) {
//            if (!coordinates.contains(new Coordinate(minX, i)) || !coordinates.contains(new Coordinate(maxX, i))) {
//                System.out.println("Missing coordinate at Y=" + i + " for rectangle between " + curr + " and " + next);
//                return false;
//            }
//        }
//        return true;
//    }

}
