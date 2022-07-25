package hu.jhamrak.day2;

public class Instruction {

    private Command command;
    private Integer unit;

    public Instruction(Command command, Integer unit) {
        this.command = command;
        this.unit = unit;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }
}
