package aoc25.day7;

import java.util.Objects;

public class WeightedBeam {

    private int beam;
    private Long weight;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WeightedBeam that = (WeightedBeam) o;
        return beam == that.beam;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(beam);
    }

    public WeightedBeam(int beam) {
        this.beam = beam;
        this.weight = 1L;
    }

    public WeightedBeam(int beam, Long weight) {
        this.beam = beam;
        this.weight = weight;
    }

    public int getBeam() {
        return beam;
    }

    public void setBeam(int beam) {
        this.beam = beam;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public void increaseWeight(Long weight) {
        this.weight+=weight;
    }
}
