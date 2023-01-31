import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day3 extends AoC {
    protected Day3() throws IOException {
        super(3);

    }

    public void init() {
    }

    public void part1() {
        var sum = input.lines().mapToInt(l -> {
            var p1 = l.substring(0, l.length() / 2);
            var p2 = l.substring(l.length() / 2, l.length());
            return (helper2(List.of(p1, p2)));
        }).sum();
        System.out.println(sum);
    }

    public String helper1(String a, String b) {
        return a.chars().filter(i1 -> b.chars().anyMatch(i2 -> i1 == i2))
                .distinct()
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }

    public int helper2(List<String> str) {
        var c = str.stream().reduce(this::helper1)
                .get().charAt(0);
        return c < 'a' ? c - 'A' + 27 : c - 'a' + 1;
    }

    public void part2() {
        var count = IntStream.range(0, input.length()).iterator();
        var sum = input.lines()
                .collect(Collectors.groupingBy(c -> count.nextInt() / 3))
                .values().stream()
                .mapToInt(this::helper2)
                .sum();
        System.out.println(sum);
    }
}
