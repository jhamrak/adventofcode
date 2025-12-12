package aoc25.day12;

import common.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final List<Integer> presentSizes = new ArrayList<>();
    private static final List<Integer> treeAreas = new ArrayList<>();
    private static final Map<Integer, List<Integer>> treeToPresentQuantities = new HashMap<>();

    public static void main(String[] args) {
        List<String> data = Helper.readLines(Main.class, Helper.INPUT);
        Long result = part1(data);
        System.out.println(result);
    }

    private static Long part1(List<String> data) {
        Long result = 0L;
        for (int i = 0; i < data.size(); i++) {
            int presentSize = 0;
            if (data.get(i).length() == 2) {
                for (char c : data.get(i + 1).toCharArray()) {
                    if (c == '#') {
                        presentSize++;
                    }
                }
                for (char c : data.get(i + 2).toCharArray()) {
                    if (c == '#') {
                        presentSize++;
                    }
                }
                for (char c : data.get(i + 3).toCharArray()) {
                    if (c == '#') {
                        presentSize++;
                    }
                }
                presentSizes.add(presentSize);
                i += 4;
            } else {
                String[] parts = data.get(i).split(": ");
                String[] treeAreaNumbers = parts[0].split("x");
                treeAreas.add(Integer.parseInt(treeAreaNumbers[0]) * Integer.parseInt(treeAreaNumbers[1]));
                String[] presents = parts[1].split(" ");
                for (String present : presents) {
                    treeToPresentQuantities.computeIfAbsent(treeAreas.size() - 1, k -> new ArrayList<>());
                    treeToPresentQuantities.get(treeAreas.size() - 1).add(Integer.parseInt(present));
                }
            }
        }

        System.out.println(presentSizes);
        System.out.println(treeAreas);
        System.out.println(treeToPresentQuantities);

        for (Map.Entry<Integer, List<Integer>> entry : treeToPresentQuantities.entrySet()) {
            int sum = 0;
            for (int i = 0; i < entry.getValue().size(); i++) {
                sum += entry.getValue().get(i) * presentSizes.get(i);
            }
            if (sum <= treeAreas.get(entry.getKey())) {
                result++;
            }
        }

        return result;
    }

}
