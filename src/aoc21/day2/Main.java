package aoc21.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> commands = Files.readAllLines(Paths.get("day2.txt"));
        calculatePosition(commands);
        calculatePositionWithAim(commands);
    }

    private static void calculatePosition(List<String> commands) {
        int horizontal = 0;
        int depth = 0;
        Map<String, Integer> commandMap = commands.stream().collect(Collectors.toMap(command -> command.substring(0, command.indexOf(" ")), command -> Integer.parseInt(command.substring(command.indexOf(" ") + 1)), Integer::sum));
        for (Map.Entry<String, Integer> command : commandMap.entrySet()) {
            switch (command.getKey()) {
                case "forward":
                    horizontal += command.getValue();
                    break;
                case "down":
                    depth += command.getValue();
                    break;
                case "up":
                    depth -= command.getValue();
                    break;
                default:
                    throw new IllegalArgumentException("command not found!");
            }

        }
        System.out.println(horizontal);
        System.out.println(depth);
        System.out.println(horizontal * depth);
    }

    private static void calculatePositionWithAim(List<String> commands) {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        List<Instruction> instructionList = commands.stream().map(commandString -> new Instruction(Command.valueOf(commandString.substring(0, commandString.indexOf(" ")).toUpperCase()), Integer.parseInt(commandString.substring(commandString.indexOf(" ") + 1)))).collect(Collectors.toList());
        for (Instruction instruction : instructionList) {
            switch (instruction.getCommand()) {

                //forward X does two things:
                //It increases your horizontal position by X units.
                //It increases your depth by your aim multiplied by X.
                case FORWARD:
                    horizontal += instruction.getUnit();
                    depth += aim * instruction.getUnit();
                    break;
                case DOWN:
                    aim += instruction.getUnit();
                    break;
                case UP:
                    aim -= instruction.getUnit();
                    break;
                default:
                    throw new IllegalArgumentException("command not found!");
            }

        }
        System.out.println(horizontal);
        System.out.println(depth);
        System.out.println(horizontal * depth);
    }
}
