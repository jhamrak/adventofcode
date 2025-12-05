package aoc25.day5;

import common.Helper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        List<String> data = Helper.readLines(Main.class, Helper.INPUT);
//        Long result = part1(data);
        Long result = part2(data);
        System.out.println(result);
    }

    private static Long part1(List<String> data) {
        Long result = 0L;

        Set<Interval> freshIds = new HashSet<>();
        boolean intervals = true;
        Iterator dataIterator = data.iterator();
        // frissek kigyűjtése
        while (intervals) {
            String line = (String) dataIterator.next();
            // üres sorban kilép
            if (!line.contains("-")) {
                intervals = false;
            } else {

                String[] split = line.split("-");
                Long intervalStart = Long.parseLong(split[0]);
                Long intervalEnd = Long.parseLong(split[1]);
                freshIds.add(new Interval(intervalStart, intervalEnd));
            }
        }
        // elérhető id-k
        while (dataIterator.hasNext()) {
            String line = (String) dataIterator.next();
            Long availableId = Long.parseLong(line);
            // végigmegyünk az intervallumokon
            for (Interval interval : freshIds) {
                // ha az adott id beleesik az intervallumba
                if (interval.getStart() <= availableId && availableId <= interval.getEnd()) {
                    result += 1;
                    break;
                }
            }
        }

        return result;
    }

    private static Long part2(List<String> data) {
        Iterator dataIterator = data.iterator();
        boolean intervals = true;
        List<Interval> freshIds = new ArrayList<>();
        // frissek kigyűjtése
        while (intervals) {
            String line = (String) dataIterator.next();
            // üres sorban kilép
            if (!line.contains("-")) {
                intervals = false;
            } else {

                String[] split = line.split("-");
                Long intervalStart = Long.parseLong(split[0]);
                Long intervalEnd = Long.parseLong(split[1]);
                freshIds.add(new Interval(intervalStart, intervalEnd));
            }
        }
        // interval start alapján rendezés, hogy könnyen mergelhetőek legyenek
        freshIds.sort(Comparator.comparingLong(Interval::getStart));

        Set<Interval> finalFreshIds = new HashSet<>();
        for (int i = 0; i < freshIds.size(); i++) {
            Interval current = new Interval(freshIds.get(i).getStart(), freshIds.get(i).getEnd());
            // amíg a következő elemmel van overlap, mergelés
            while (i < freshIds.size() - 1 && current.getEnd() >= freshIds.get(i + 1).getStart()) {
                current = new Interval(current.getStart(), Math.max(current.getEnd(), freshIds.get(i + 1).getEnd()));
                // amit belemergelünk, azt már ne nézze újra
                i++;
            }
            finalFreshIds.add(current);
        }
        // végleges intervallumok hosszainak összege
        return finalFreshIds.stream().mapToLong(i -> i.getEnd() - i.getStart() + 1).sum();
    }


}
