package aoc25.day10;

import common.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static List<Boolean> lights = new ArrayList<>();
    static List<Integer> joltages = new ArrayList<>();
    static List<List<Integer>> buttons = new ArrayList<>();

    public static void main(String[] args) {
        List<String> data = Helper.readLines(Main.class, Helper.INPUT);
        Long result = part1(data);
//        Long result = part2(data);
        System.out.println(result);
    }

    private static Long part1(List<String> data) {
        Long result = 0L;
        for (String line : data) {
            lights = new ArrayList<>();
            buttons = new ArrayList<>();

            String parts[] = line.split(" ");
            for (char c : parts[0].toCharArray()) {
                if (c == '[' || c == ']') {
                    continue;
                }
                lights.add(c == '#');
            }
            for (int i = 1; i < parts.length - 1; i++) {
                String part = parts[i];
                part = part.substring(1, part.length() - 1);
                String nums[] = part.split(",");
                buttons.add(new ArrayList<>(Arrays.stream(nums).mapToInt(Integer::parseInt).boxed().toList()));

            }
//            System.out.println(lights);
//            System.out.println(buttons);

            List<Boolean> emptyLights = new ArrayList<>();
            lights.forEach(m -> emptyLights.add(false));
            Long res = pushButtonRecursive(emptyLights, buttons, false, 0, 0L);
            System.out.println(res);
            result += res;

        }
        System.out.println("----");
        return result;

    }

    private static Long pushButtonRecursive(List<Boolean> lights, List<List<Integer>> buttons, boolean used, int currLength, Long currPresses) {
//        System.out.println(" >> lights: " + lights + "buttons: " + buttons + " used: " + used + " currLength: " + currLength + " currPresses: " + currPresses);
        if (currLength > buttons.size()) {
//            System.out.println(lights + " buttons " + currLength + " used: " + used + " presses: " + currPresses);
            for (int i = 0; i < lights.size(); i++) {
                if (lights.get(i) != Main.lights.get(i)) {
                    return Long.MAX_VALUE;
                }
            }
            return currPresses;
        } else {
            if (used) {
                currPresses++;
                for (int pos : buttons.get(currLength - 1)) {
                    lights.set(pos, !lights.get(pos));
                }

            }
            Long withNextFalse = pushButtonRecursive(new ArrayList<>(lights), buttons, false, currLength + 1, currPresses);
            Long withNextTrue = pushButtonRecursive(new ArrayList<>(lights), buttons, true, currLength + 1, currPresses);
//             Long withNextTrue = Long.MAX_VALUE;
//            System.out.println(Math.min(solution, Math.min(withNextFalse, withNextTrue)) + " at length " + currLength + " used: " + used);
//            System.out.println(Math.min(withNextFalse, withNextTrue));
            return Math.min(withNextFalse, withNextTrue);
        }
    }


    private static Long part2(List<String> data) {
        Long result = 0L;


        for (String line : data) {
            lights = new ArrayList<>();
            buttons = new ArrayList<>();

            String parts[] = line.split(" ");
            for (char c : parts[0].toCharArray()) {
                if (c == '[' || c == ']') {
                    continue;
                }
                lights.add(c == '#');
            }
            for (int i = 1; i < parts.length - 1; i++) {
                String part = parts[i];
                part = part.substring(1, part.length() - 1);
                String nums[] = part.split(",");
                buttons.add(new ArrayList<>(Arrays.stream(nums).mapToInt(Integer::parseInt).boxed().toList()));

            }

            Arrays.stream(parts[parts.length - 1].substring(1, parts[parts.length - 1].length() - 1).split(",")).forEach(j -> joltages.add(Integer.parseInt(j)));

            System.out.println(buttons);
            System.out.println(joltages);

// TODO


        }
        System.out.println("----");
        return result;
    }

}
