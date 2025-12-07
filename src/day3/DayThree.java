package day3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DayThree {

    static void main() {

        System.out.println(-10 % 100);

        List<String> testRows = readFile("test_input.txt");
        int testAns1 = maxBatteryValue(testRows);
        System.out.println("Test answer 1 : " + testAns1);
        long testAns2 = maxBatteryValueNonBrute(testRows, 2);
        System.out.println("Test answer 2: " + testAns2);

        List<String> ansRows = readFile("input.txt");
        int realAns = maxBatteryValue(ansRows);
        System.out.println("Real answer: " + realAns);
        long realAns2 = maxBatteryValueNonBrute(ansRows, 12);
        System.out.println("Real answer 2 : " + realAns2);


//        int testAns2 = parseInstructionsTwoStar(testRows);
//        System.out.println("Test answer 2: " + testAns2);
//        int realAns2 = parseInstructionsTwoStar(ansRows);
//        System.out.println("Real answer 2: " + realAns2);
    }

    static List<String> readFile(String path) {
        File f = new File("src/day3/" + path);
        try (FileReader fileReader = new FileReader(f)) {
            return fileReader.readAllLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int maxBatteryValue(List<String> input) {
        return input.stream().map(r -> {
            int maxValue = -1;
            for (int i = 0; i < r.length(); i++) {
                for (int j = i + 1; j < r.length(); j++) {
                    if (i == j) {
                        continue;
                    }
                    int candidate = Integer.parseInt("" + r.charAt(i) + r.charAt(j));
                    if (candidate > maxValue) {
                        maxValue = candidate;
                    }
                }
            }
            return maxValue;
        }).reduce(0, Integer::sum);
    }

    static Long maxBatteryValueNonBrute(List<String> input, int num) {
        // Order matters
        // Find highest n values from the list

        return input.stream()
                .map(i -> maxBatteryValueNonBruteSingle(i, num))
                .peek(i -> System.out.println(i))
                .map(Long::parseLong)
                .reduce(0L, Long::sum);
    }

    static String maxBatteryValueNonBruteSingle(String battery, int numValues) {

        if (battery.isEmpty() || numValues <= 0) {
            return "";
        }

        int index = -1;
        int max = -1;
        for (int i = 0; i < battery.length() - numValues + 1; i++) {
            int curr = battery.charAt(i);
            if (curr > max) {
                max = curr;
                index = i;
            }
        }

        return battery.charAt(index) + maxBatteryValueNonBruteSingle(battery.substring(index + 1), numValues - 1);
    }

}
