import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day11 extends AoC{
    List<Monkey> monkeys;
    long[] inspects;
    int cM;
    public Day11() throws IOException {
        super(11);
    }

    private static class Monkey{
        List<Long> items;
        String[] op;
        int div, trueTo, falseTo;
        long inspects;
        public Monkey (String string){
            var lines = string.split("\n");
            items = Arrays.stream(lines[1].split(":")[1].split(","))
                    .map(i -> Long.parseLong(i.trim())).collect(Collectors.toCollection(ArrayList::new));
            op = lines[2].split("= old")[1].trim().split(" ");
            div = Integer.parseInt(lines[3].split("by ")[1].trim());
            trueTo = Integer.parseInt(lines[4].split("monkey ")[1].trim());
            falseTo = Integer.parseInt(lines[5].split("monkey ")[1].trim());
        }
        public long worryLevel(long level){
            var val = op[1].equals("old") ? level : Long.parseLong(op[1]);
            return switch (op[0]) {
                case "*" -> level * val;
                case "+" -> level + val;
                default -> throw new IllegalArgumentException();
            };
        }
    }
    public void init() {
        monkeys = Arrays.stream(input.split("((\\n\\r)|(\\r\\n)){2}|(\\r){2}|(\\n){2}"))
                .map(Monkey::new)
                .toList();
        cM = monkeys.stream().mapToInt(m -> m.div).reduce(1, (a,b) -> (a*b));
    }

    public void part1() {
        helper1(20, 1);
    }

    public void helper1(int round, int part){
        for (int j = 0; j < round; j++) {
            for (var m : monkeys) {
                for (var i : m.items) {
                    m.inspects++;
                    var level = m.worryLevel(i) / (part == 1 ? 3 : 1) % cM;
                    var target = level % m.div == 0 ? m.trueTo : m.falseTo;
                    monkeys.get(target).items.add(level);
                }
                m.items.clear();
            }
        }
        var res = monkeys.stream()
                .map(s -> s.inspects)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .reduce(1L, (a, b) -> a*b);
        System.out.println(res);
    }

    public void part2() {
        init();
        helper1(10000, 2);
    }
}
