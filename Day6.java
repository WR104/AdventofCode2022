import java.io.IOException;
import java.util.stream.IntStream;

public class Day6 extends AoC{

    public Day6() throws IOException {
        super(6);
    }

    public void init() {

    }

    public void part1() {
        System.out.println(helper2(4));
    }
    public boolean helper1(String str){
        return str.chars().distinct().count() == str.length();
    }

    public int helper2(int len){
        return IntStream.range(0, input.length()-len)
                .filter(i -> helper1(input.substring(i, i+len)))
                .findFirst()
                .getAsInt() + len;
    }

    public void part2() {
        System.out.println(helper2(14));
    }
}
