package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Helper {

    public static final String INPUT = "input.txt";
    public static final String TEST = "test.txt";

    public static List<String> readLines(Class clazz, String name) {
        return new BufferedReader(new InputStreamReader(
                clazz.getResourceAsStream(name)
        )).lines().toList();
    }
}
