import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Day1 extends AoC {

    public Day1() throws IOException {
        super(1);
    }


    public void init() {

    }

    public void part1() {
        List<Integer> list =
                Arrays.stream(input.split("\\r?\\n\\r?\\n"))
                        .map(s -> s.lines().mapToInt(Integer::parseInt).sum())
                        .sorted()
                        .toList();
        System.out.println(list.get(list.size()-1));
    }

    public void part2(){
        List<Integer> list =
                Arrays.stream(input.split("\\r?\\n\\r?\\n"))
                        .map(s -> s.lines().mapToInt(Integer::parseInt).sum())
                        .sorted()
                        .toList();
        int res = list.stream()
                .sorted()
                .skip(list.size() - 3)
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(res);
    }
}
