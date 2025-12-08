package aoc25.day8;

import java.util.Objects;

public class Distance {

    private int firstIndex;
    private int secondIndex;
    private double d;

    public Distance(int firstIndex, int secondIndex, double d) {
        this.firstIndex = firstIndex;
        this.secondIndex = secondIndex;
        this.d = d;
    }

    public Distance() {
    }

    @Override
    public String toString() {
        return "Distance{" +
                "firstIndex=" + firstIndex +
                ", secondIndex=" + secondIndex +
                ", d=" + d +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return firstIndex == distance.firstIndex && secondIndex == distance.secondIndex && d == distance.d;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstIndex, secondIndex, d);
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getSecondIndex() {
        return secondIndex;
    }

    public void setSecondIndex(int secondIndex) {
        this.secondIndex = secondIndex;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }
}
