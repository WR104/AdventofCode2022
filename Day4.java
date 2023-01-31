import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day4 extends AoC {
    public Day4() throws IOException {
        super(4);
    }

    public void init() {
    }

    public void part1() {
        var sum = Arrays.stream(input.split("\\R"))
                .map(this::helper0)
                .filter(this::helper1)
                .count();
        System.out.println(sum);
    }

    private List<String> helper0(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        var matcher = pattern.matcher(input);
        List<String> digits = new ArrayList<>();
        while (matcher.find())
            digits.add(matcher.group());
        return digits;
    }

    private boolean helper1(List<String> str) {
        var list = str.stream()
                .map(Integer::parseInt).toList();
        return (list.get(0) <= list.get(2) && list.get(1) >= list.get(3)
                || list.get(0) >= list.get(2) && list.get(1) <= list.get(3));
    }

    public void part2() {
        var sum = Arrays.stream(input.split("\\R"))
                .map(this::helper0)
                .filter(this::helper2)
                .count();
        System.out.println(sum);
    }

    private boolean helper2(List<String> str) {
        var list = str.stream()
                .map(Integer::parseInt).toList();
        return IntStream.range(list.get(0), list.get(1) + 1)
                .anyMatch(i -> i >= list.get(2) && i <= list.get(3));
    }
}
