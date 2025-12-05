package aoc25.day5;

public class Interval {
    private Long start;
    private Long end;

    public Interval(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    public Long getStart() {
        return start;
    }

    public Long getEnd() {
        return end;
    }
}
