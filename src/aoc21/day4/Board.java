package aoc21.day4;

import java.util.List;

public class Board {

    private List<List<Number>> numbers;

    public Board(List<List<Number>> numbers) {
        this.numbers = numbers;
    }

    public List<List<Number>> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<List<Number>> numbers) {
        this.numbers = numbers;
    }
}
