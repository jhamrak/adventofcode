package aoc21.day4;

public class Number {

    private int value;
    private boolean marked;

    public Number(int value, boolean marked) {
        this.value = value;
        this.marked = marked;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        return value + (marked ? "+" : "-");
    }
}
