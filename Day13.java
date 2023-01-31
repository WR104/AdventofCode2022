import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 extends AoC {
    public List<Pair> all;
    public Day13() throws IOException {
        super(13);
    }

    record Pair(String s1, String s2) {
    }

    public void init() {
        all = Arrays.stream(input.split("((\\n\\r)|(\\r\\n)){2}|(\\r){2}|(\\n){2}"))
                .map(s -> new Pair(s.split("\\n")[0], s.split("\\n")[1]))
                .toList();
    }

    public void helper1(Pair p){

    }

    public void part1() {
        helper1(all.get(1));
    }

    public void part2() {

    }
}
