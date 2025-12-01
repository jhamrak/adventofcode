package aoc25.day1;

import common.Helper;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> rotations = Helper.readLines(Main.class, "input.txt");
//        part1(rotations);
        part2(rotations);

    }

    private static void part1(List<String> rotations) {
        int safe = 50;
        int counter = 0;
        for(String rotation : rotations) {
            System.out.println(">> Safe: " + safe + ", rotation: " + rotation);
            int rotationNum = Integer.parseInt(rotation.substring(1)) % 100;
            // negatív irányba forgatjuk
            if(rotation.startsWith("L")) {
                // átforog nullán
                if(rotationNum > safe) {

                    safe = 100 - rotationNum + safe;
                } else {
                    safe = safe - rotationNum;
                }
                // pozitív irányba forgatjuk
            } else {
                safe = (rotationNum + safe) % 100;
            }
            if(safe == 0) {
                counter++;
            }

            System.out.println("<< Safe: " + safe + ", counter: " + counter);
        }
        System.out.println(counter);
    }

    private static void part2(List<String> rotations) {
        int safe = 50;
        int counter = 0;
        for(String rotation : rotations) {
            System.out.println(">> Safe: " + safe + ", rotation: " + rotation);
            int rotationNum = Integer.parseInt(rotation.substring(1)) % 100;
            counter += Integer.parseInt(rotation.substring(1)) / 100;
            if(rotationNum != 0) {
                // negatív irányba forgatjuk
                if (rotation.startsWith("L")) {
                    // átforog nullán
                    if (rotationNum > safe) {
                        System.out.println(">> Left through 0");
                        if(safe != 0) {
                            counter++;
                        }
                        safe = 100 - rotationNum + safe;
                    } else {
                        safe = safe - rotationNum;
                        if (safe == 0) {
                            counter++;
                        }
                    }
                    // pozitív irányba forgatjuk
                } else {
                    counter += (rotationNum + safe) / 100;
                    safe = (rotationNum + safe) % 100;
                }
            }

            System.out.println("<< Safe: " + safe + ", counter: " + counter);
        }
        System.out.println(counter);
    }

}
