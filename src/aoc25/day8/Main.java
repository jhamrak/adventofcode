package aoc25.day8;

import common.Helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final int STEPS = 1000;

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
            coordinates.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }
        List<Distance> distances = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate ci = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                Coordinate cj = coordinates.get(j);
                double distance = Math.sqrt(Math.pow(ci.getX() - cj.getX(), 2) + Math.pow(ci.getY() - cj.getY(), 2) + Math.pow(ci.getZ() - cj.getZ(), 2));
                distances.add(new Distance(i, j, distance));
            }
        }
//        System.out.println(distances);
        distances.sort(Comparator.comparingDouble(Distance::getD));
        System.out.println(distances);

        List<Circuit> circuits = new ArrayList<>();
//        for(int i = 0; i < 10; i++) {
        for (int i = 0; i < 1000; i++) {
            boolean joined = false;
            Circuit merge1 = null;
            Circuit merge2 = null;
            for (Circuit circuit : circuits) {
                if (circuit.getIndexes().contains(distances.get(i).getFirstIndex()) || circuit.getIndexes().contains(distances.get(i).getSecondIndex())) {

                    circuit.getIndexes().add(distances.get(i).getFirstIndex());
                    circuit.getIndexes().add(distances.get(i).getSecondIndex());
                    for (Circuit otherCircuit : circuits) {
                        if (otherCircuit != circuit &&
                                (otherCircuit.getIndexes().contains(distances.get(i).getFirstIndex()) ||
                                        otherCircuit.getIndexes().contains(distances.get(i).getSecondIndex()))) {
                            merge1 = circuit;
                            merge2 = otherCircuit;
                        }
                    }
                    joined = true;
                    break;
                }
            }
            if (merge1 != null && merge2 != null) {
                merge1.getIndexes().addAll(merge2.getIndexes());
                circuits.remove(merge2);
            }
            if (!joined) {
                Circuit circuit = new Circuit();
                circuit.getIndexes().add(distances.get(i).getFirstIndex());
                circuit.getIndexes().add(distances.get(i).getSecondIndex());
                circuits.add(circuit);
            }
            System.out.println(distances.get(i).getFirstIndex() + " - " + distances.get(i).getSecondIndex());
            System.out.println(circuits);
        }
        circuits.sort(Comparator.comparingInt(c -> c.getIndexes().size()));
        System.out.println(circuits);
        result = (long) (circuits.get(circuits.size() - 3).getIndexes().size() * circuits.get(circuits.size() - 2).getIndexes().size() * circuits.get(circuits.size() - 1).getIndexes().size());
        return result;
    }

    private static Long part2(List<String> data) {
        Long result = 0L;

        List<Coordinate> coordinates = new ArrayList<>();
        for (String line : data) {
            String[] parts = line.split(",");
            coordinates.add(new Coordinate(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }
        List<Distance> distances = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate ci = coordinates.get(i);
            for (int j = i + 1; j < coordinates.size(); j++) {
                Coordinate cj = coordinates.get(j);
                double distance = Math.sqrt(Math.pow(ci.getX() - cj.getX(), 2) + Math.pow(ci.getY() - cj.getY(), 2) + Math.pow(ci.getZ() - cj.getZ(), 2));
                distances.add(new Distance(i, j, distance));
            }
        }
//        System.out.println(distances);
        distances.sort(Comparator.comparingDouble(Distance::getD));
        System.out.println(distances);

        List<Circuit> circuits = new ArrayList<>();
//        for(int i = 0; i < 10; i++) {
        for (int i = 0; i < STEPS; i++) {
            boolean joined = false;
            Circuit merge1 = null;
            Circuit merge2 = null;
            for (Circuit circuit : circuits) {
                if (circuit.getIndexes().contains(distances.get(i).getFirstIndex()) || circuit.getIndexes().contains(distances.get(i).getSecondIndex())) {

                    circuit.getIndexes().add(distances.get(i).getFirstIndex());
                    circuit.getIndexes().add(distances.get(i).getSecondIndex());
                    for (Circuit otherCircuit : circuits) {
                        if (otherCircuit != circuit &&
                                (otherCircuit.getIndexes().contains(distances.get(i).getFirstIndex()) ||
                                        otherCircuit.getIndexes().contains(distances.get(i).getSecondIndex()))) {
                            merge1 = circuit;
                            merge2 = otherCircuit;
                        }
                    }
                    joined = true;
                    break;
                }
            }
            if (merge1 != null && merge2 != null) {
                merge1.getIndexes().addAll(merge2.getIndexes());
                circuits.remove(merge2);
            }
            if (!joined) {
                Circuit circuit = new Circuit();
                circuit.getIndexes().add(distances.get(i).getFirstIndex());
                circuit.getIndexes().add(distances.get(i).getSecondIndex());
                circuits.add(circuit);
            }

//            System.out.println(distances.get(i).getFirstIndex() + " - " + distances.get(i).getSecondIndex());
//            System.out.println(circuits);
        }

        System.out.println(circuits);

        for (int i = STEPS; i < distances.size(); i++) {

            boolean joined = false;
            Circuit merge1 = null;
            Circuit merge2 = null;
            for (Circuit circuit : circuits) {
                if (circuit.getIndexes().contains(distances.get(i).getFirstIndex()) || circuit.getIndexes().contains(distances.get(i).getSecondIndex())) {


                    circuit.getIndexes().add(distances.get(i).getFirstIndex());
                    circuit.getIndexes().add(distances.get(i).getSecondIndex());
                    for (Circuit otherCircuit : circuits) {
                        if (otherCircuit != circuit &&
                                (otherCircuit.getIndexes().contains(distances.get(i).getFirstIndex()) ||
                                        otherCircuit.getIndexes().contains(distances.get(i).getSecondIndex()))) {
                            merge1 = circuit;
                            merge2 = otherCircuit;
                        }
                    }
                    joined = true;
                    break;
                }
            }
            if (merge1 != null && merge2 != null) {
                merge1.getIndexes().addAll(merge2.getIndexes());
                circuits.remove(merge2);
            }
            if (!joined) {
                Circuit circuit = new Circuit();
                circuit.getIndexes().add(distances.get(i).getFirstIndex());
                circuit.getIndexes().add(distances.get(i).getSecondIndex());
                circuits.add(circuit);
            }

            System.out.println(distances.get(i).getFirstIndex() + " - " + distances.get(i).getSecondIndex());
            System.out.println(circuits);
            System.out.println(result);

            if (circuits.size() == 1 && circuits.get(0).getIndexes().size() == coordinates.size()) {
                return (long) (coordinates.get(distances.get(i).getFirstIndex()).getX() * coordinates.get(distances.get(i).getSecondIndex()).getX());

            }

        }
        return 0L;

    }

}
