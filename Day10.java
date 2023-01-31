import java.io.IOException;

public class Day10 extends AoC {
    int[] C;
    int cycle, value, ss, indexC;
    String[][] S;

    public Day10() throws IOException {
        super(10);
    }

    public void init() {
        ss = 0;
        C = new int[]{20, 60, 100, 140, 180, 220};
        S = new String[6][40];
        for (int i = 0; i < S.length; i++) {
            for (int j = 0; j < 40; j++)
                S[i][j] = "?";
        }
    }

    public void part1() {
        cycle = 0;
        value = 1;
        indexC = 0;
        int t = 0;
        for (var s : input.split("\n")) {
            if (s.trim().split(" ").length == 1) {
                t += 1;
                helper1(t);
            } else {
                t += 1;
                helper1(t);
                t += 1;
                helper1(t);
                value += Integer.parseInt(s.trim().split(" ")[1]);
            }
        }
        System.out.println(ss);

    }

    public void helper1(int t) {
        for (var i : C)
            if (i == t)
                ss += t * value;
        S[(t - 1) / 40][(t - 1) % 40] = Math.abs(value - (t - 1) % 40) <= 1 ? "#" : " ";
    }

    public void part2() {
        for (var strings : S) {
            for (var s : strings)
                System.out.print(s);
            System.out.println();
        }
    }
}