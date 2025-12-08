package aoc25.day8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Circuit {
    private Set<Integer> indexes = new HashSet<>();

    public Set<Integer> getIndexes() {
        return indexes;
    }

    public void setIndexes(Set<Integer> indexes) {
        this.indexes = indexes;
    }

    @Override
    public String toString() {
        return "Circuit{" +
                "indexes=" + indexes +
                '}';
    }
}
