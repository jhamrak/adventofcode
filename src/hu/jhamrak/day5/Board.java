package hu.jhamrak.day5;

import java.util.List;

public class Board {

    private List<List<Integer>> numbers;

    public Board(List<List<Integer>> numbers) {
        this.numbers = numbers;
    }

    public List<List<Integer>> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<List<Integer>> numbers) {
        this.numbers = numbers;
    }
}
