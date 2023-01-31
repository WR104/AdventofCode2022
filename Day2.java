import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day2 extends AoC {
    Map<String, String> v = new HashMap<>();
    Map<String, String> d = new HashMap<>();
    Map<String, Integer> score = new HashMap<>();

    public Day2() throws IOException {
        super(2);

    }

    public void init(){}
    @Override
    public void part1() {
        //win
        v.put("A", "Y");
        v.put("B", "Z");
        v.put("C", "X");
        //loss
        d.put("A", "Z");
        d.put("B", "X");
        d.put("C", "Y");

        score.put("X", 1);
        score.put("Y", 2);
        score.put("Z", 3);

        String[] list = input.split("\\n");
        int res = 0;
        for (String str : list) {
            String[] s = str.split(" ");
            s[1] = s[1].trim();
            if (v.get(s[0]).equals(s[1]))
                res += score.get(s[1]) + 6;
            else if (d.get(s[0]).equals(s[1]))
                res += score.get(s[1]);
            else
                res += score.get(s[1]) + 3;

        }
        System.out.println(res);
    }

    @Override
    public void part2() {
        String[] list = input.split("\\n");
        Map<String, String> tie = new HashMap<>();
        tie.put("A", "X");
        tie.put("B", "Y");
        tie.put("C", "Z");
        int res = 0;
        for (String str : list) {
            String[] s = str.split(" ");
            s[1] = s[1].trim();
            if (s[1].equals("X"))
                res += score.get(d.get(s[0]));
            else if (s[1].equals("Y"))
                res += score.get(tie.get(s[0])) + 3;
            else
                res += score.get(v.get(s[0])) + 6;
        }
        System.out.println(res);
    }
}
