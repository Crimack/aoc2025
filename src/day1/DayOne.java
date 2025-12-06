package day1;

import java.io.*;
import java.util.List;

public class DayOne {

    static void main() {

        System.out.println(-10 % 100);

        List<String> testRows = readFile("test_input.txt");
        int testAns = parseInstructions(testRows);
        System.out.println("Test answer: " + testAns);

        List<String> ansRows = readFile("input.txt");
        int realAns = parseInstructions(ansRows);
        System.out.println("Real answer: " + realAns);


        int testAns2 = parseInstructionsTwoStar(testRows);
        System.out.println("Test answer 2: " + testAns2);
        int realAns2 = parseInstructionsTwoStar(ansRows);
        System.out.println("Real answer 2: " + realAns2);
    }

    static List<String> readFile(String path) {
        File f = new File("src/day1/" + path);
        try (FileReader fileReader = new FileReader(f)) {
                return fileReader.readAllLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int parseInstructions(List<String> instructions) {
        int curr = 50;
        int numZeros = 0;

        for (String instruction : instructions) {
            char direction = instruction.charAt(0);
            int magnitude = Integer.parseInt(instruction.substring(1));

            if (direction == 'L') {
                curr -= magnitude;
            } else {
                curr += magnitude;
            }

            curr = curr % 100;

            if (curr == 0) {
                numZeros++;
            }
        }

        return numZeros;
    }

    static int parseInstructionsTwoStar(List<String> instructions) {
        int curr = 50;
        int numPassZero = 0;

        for (String instruction : instructions) {
            char direction = instruction.charAt(0);
            int magnitude = Integer.parseInt(instruction.substring(1));

            if (direction == 'L') {
                curr -= magnitude;
            } else {
                curr += magnitude;
            }

            if (curr > 99) {
                curr -= 100;
                numPassZero++;
            } else if (curr < 0) {
                curr += 100;
                numPassZero++;
            } else if (curr == 0) {
                numPassZero++;
            }
        }

        return numPassZero;
    }
}
