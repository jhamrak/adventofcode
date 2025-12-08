package aoc25.day7;

import common.Helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        List<String> data = Helper.readLines(Main.class, Helper.INPUT);
//        Long result = part1(data);
//        part2_v1(data);
//        System.out.println("-----");
        Long result = part2(data);
        System.out.println(result);
    }

    private static Long part1(List<String> data) {
        Long result = 0L;

        Set<Integer> beams = new HashSet<>();

        String firstLine = data.get(0);
        for(int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) == 'S') {
                beams.add(i);
            }
        }

        for(int j = 1; j < data.size(); j++) {
            String line = data.get(j);

            Set<Integer> nextBeams = new HashSet<>(beams);
            for (int beam : beams) {
                if (line.charAt(beam) == '^') {
                    result++;
                    nextBeams.add(beam - 1);
                    nextBeams.add(beam + 1);
                    nextBeams.remove(beam);
                }
            }
            beams = nextBeams;
        }



        return result;
    }

    private static Long part2_v1(List<String> data) {
        Long result = 1L;

        List<Integer> beams = new ArrayList<>();

        String firstLine = data.get(0);
        for(int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) == 'S') {
                beams.add(i);
            }
        }

        for(int j = 1; j < data.size(); j++) {
            String line = data.get(j);

            List<Integer> nextBeams = new ArrayList<>(beams);
            for (int beam : beams) {
                if (line.charAt(beam) == '^') {
                    result+=1;
                    nextBeams.add(beam - 1);
                    nextBeams.add(beam + 1);
                    for(int i = 0; i < nextBeams.size(); i++) {
                        if(nextBeams.get(i) == beam) {
                            nextBeams.remove(i);
                        }
                    }

                }
            }
            beams = nextBeams;
            System.out.println(result);
        }



        return result;
    }

//    private static Long part2(List<String> data) {
//        Long result = 1L;
//
//        Set<WeightedBeam> beams = new HashSet<>();
//
//        String firstLine = data.get(0);
//        for(int i = 0; i < firstLine.length(); i++) {
//            if (firstLine.charAt(i) == 'S') {
//                beams.add(new WeightedBeam(i));
//            }
//        }
//
//        for(int j = 1; j < data.size(); j++) {
//            String line = data.get(j);
//
//            Set<WeightedBeam> nextBeams = new HashSet<>(beams);
//            for (WeightedBeam beam : beams) {
//                if (line.charAt(beam.getBeam()) == '^') {
//                    result+=beam.getWeight();
//
//                    if(nextBeams.contains(new WeightedBeam(beam.getBeam() - 1))) {
//                        nextBeams.get
//                    } else {
//                        WeightedBeam newBeam = new WeightedBeam(beam.getBeam() - 1);
//                        newBeam.setWeight(beam.getWeight());
//                        nextBeams.add(newBeam);
//                    }
//                    nextBeams.add(beam - 1);
//                    nextBeams.add(beam + 1);
//                    nextBeams.remove(beam);
//                }
//            }
//            beams = nextBeams;
//        }
//
//
//
//        return result;
//    }



    private static Long part2(List<String> data) {
        Long result = 1L;

        List<WeightedBeam> beams = new ArrayList<>();

        String firstLine = data.get(0);
        for(int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) == 'S') {
                beams.add(new WeightedBeam(i));
            }
        }

        for(int j = 1; j < data.size(); j++) {
            String line = data.get(j);

            List<WeightedBeam> nextBeams = new ArrayList<>(beams);
            for (WeightedBeam beam : beams) {
                if (line.charAt(beam.getBeam()) == '^') {
                    result+=beam.getWeight();

                    if(nextBeams.contains(new WeightedBeam(beam.getBeam() - 1))) {
                        nextBeams.get(nextBeams.indexOf(new WeightedBeam(beam.getBeam() - 1))).increaseWeight(beam.getWeight());
                    } else {
                        nextBeams.add(new WeightedBeam(beam.getBeam() - 1, beam.getWeight()));
                    }

                    if(nextBeams.contains(new WeightedBeam(beam.getBeam() + 1))) {
                        nextBeams.get(nextBeams.indexOf(new WeightedBeam(beam.getBeam() + 1))).increaseWeight(beam.getWeight());
                    } else {
                        nextBeams.add(new WeightedBeam(beam.getBeam() + 1, beam.getWeight()));
                    }

                    for(int i = 0; i < nextBeams.size(); i++) {
                        if(nextBeams.get(i).getBeam() == beam.getBeam()) {
                            nextBeams.remove(i);

                        }
                    }

                }
            }
            beams = nextBeams;
            System.out.println(result);
        }



        return result;
    }
}
