import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

public class Day9 extends AoC {
    List<entry> moves;
    Set<pos> set;

    public Day9() throws IOException {
        super(9);
    }

    record entry(String dir, int steps) {
    }

    record pos(int x, int y) {
    }

    public void init() {
        moves = new ArrayList<>();
        input.lines()
                .forEach(s -> moves.add(new entry(s.split(" ")[0], Integer.parseInt(s.split(" ")[1]))));
    }

    public pos helper1(pos head, pos tail, int index) {
        if (head.x == tail.x && head.y == tail.y) return tail;
        if (head.y == tail.y) {
            if (Math.abs(head.x - tail.x) != 1) tail = head.x > tail.x ? helper2("R", tail) : helper2("L", tail);
        } else if (head.x == tail.x) {
            if (Math.abs(head.y - tail.y) != 1) tail = head.y > tail.y ? helper2("U", tail) : helper2("D", tail);
        } else {
            if (Math.abs(head.x - tail.x) != 1 || Math.abs(head.y - tail.y) != 1) {
                tail = head.y > tail.y ? helper2("U", tail) : helper2("D", tail);
                tail = head.x > tail.x ? helper2("R", tail) : helper2("L", tail);
            }
        }
        if (index == 8) set.add(tail);
        return tail;
    }

    public pos helper2(String d, pos P) {
        if (d.equals("R")) return new pos(P.x + 1, P.y);
        if (d.equals("L")) return new pos(P.x - 1, P.y);
        if (d.equals("U")) return new pos(P.x, P.y + 1);
        if (d.equals("D")) return new pos(P.x, P.y - 1);
        return P;
    }

    public void helepr3(int size) {
        set = new HashSet<>();
        var head = new pos(0, 0);
        pos[] tails = new pos[size];
        IntStream.range(0, size).forEach(s -> tails[s] = new pos(0, 0));
        for (var move : moves)
            for (int i = 1; i <= move.steps; i++) {
                head = helper2(move.dir, head);
                tails[0] = size == 1 ? helper1(head, tails[0], 8) : helper1(head, tails[0], 0);
                IntStream.range(1, size).forEach(s -> tails[s] = helper1(tails[s - 1], tails[s], s));
            }
        System.out.println(set.size());
    }

    public void part1() {
        helepr3(1);
    }

    public void part2() {
        helepr3(9);
    }
}