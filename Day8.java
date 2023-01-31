import java.io.IOException;
import java.util.stream.IntStream;

public class Day8 extends AoC {
    String[] trees;
    public Day8() throws IOException {
        super(8);
    }

    public void init() {
        trees = input.lines().toList().toArray(new String[0]);
    }

    public void part1() {
        var res = 2 * trees[0].length() + 2 * trees.length - 4;
        for (int i = 1; i < trees.length - 1; i++)
            for (int j = 1; j < trees[0].length() - 1; j++)
                if (helper1(i, j))
                    res += 1;
        System.out.println(res);
    }

    public boolean helper1(int i, int j) {

        return IntStream.range(0, j)
                .noneMatch(s -> trees[i].charAt(s) - '0' >= trees[i].charAt(j) - '0')
                | IntStream.range(j + 1, trees[0].length())
                .noneMatch(s -> trees[i].charAt(s) - '0' >= trees[i].charAt(j) - '0')
                | IntStream.range(0, i)
                .noneMatch(s -> trees[s].charAt(j) - '0' >= trees[i].charAt(j) - '0')
                | IntStream.range(i + 1, trees.length)
                .noneMatch(s -> trees[s].charAt(j) - '0' >= trees[i].charAt(j) - '0');
    }

    public void part2() {
        int max = 0;
        for (int i = 1; i < trees.length - 1; i++)
            for (int j = 1; j < trees[0].length() - 1; j++)
                max = Math.max(max, helper2(i, j));
        System.out.println(max);
    }

    public int helper2(int i, int j) {
        int res, sum = 1, k;
        for (k = j - 1, res = 0; k >= 0; k--, res++)
            if (trees[i].charAt(k) - '0' >= trees[i].charAt(j) - '0')
                break;
        sum *= res;
        for (k = j + 1, res = 1; k < trees[0].length(); k++, res ++)
            if (trees[i].charAt(k) - '0' >= trees[i].charAt(j) - '0')
                break;
        sum *= res;
        for (k = i - 1, res = 0; k >= 0; k--, res++)
            if (trees[k].charAt(j) - '0' >= trees[i].charAt(j) - '0')
                break;
        sum *= res;
        for (k = i + 1, res = 0; k < trees.length; k++, res++)
            if (trees[k].charAt(j) - '0' >= trees[i].charAt(j) - '0')
                break;
        sum *= res;
        return sum;
    }
}
