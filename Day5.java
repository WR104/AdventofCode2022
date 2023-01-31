import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day5 extends AoC {

    List<Stack<Character>> list;
    List<Move> moves;
    List<Stack<Character>> stacks;
    int size;

    public Day5() throws IOException {
        super(5);
    }

    public void init() {
        size = 9;
        list = new ArrayList<>(size);
        IntStream.range(0,size).forEach(s -> list.add(new Stack<>()));

        for (String s : input.split("\\n")) {
            int curr = 0;
            if (s.charAt(1) == '1')
                break;
            for (int i = 1; i < s.length(); i += 4) {
                Character c = s.charAt(i);
                if(Character.isLetter(c))
                    list.get(curr++).push(s.charAt(i));
                else
                    curr += 1;
            }
        }

         stacks = list.stream().map(s -> {
            Stack<Character> temp = new Stack<>();
            while (!s.isEmpty())
                temp.push(s.pop());
            return temp;
        }).toList();

         moves= getMoves();
    }

    record Move(int count, int from, int to){ }
    public void part1() {
        for(Move m : moves){
            for (int i = 0; i < m.count; i++) {
                var top = stacks.get(m.from).pop();
                 stacks.get(m.to).push(top);
            }
        }

        stacks.forEach(s -> System.out.print(s.peek()));
        System.out.println();

    }

    private List<Move> getMoves(){
        return input.lines()
                .filter(s -> !s.equals("") && s.charAt(0) == 'm')
                .map(s -> {
                    var m = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)").matcher(s);
                    m.matches();
                    return new Move(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))-1,
                            Integer.parseInt(m.group(3))-1);
                })
                .toList();
    }


    public void part2() {
        init();
        for(Move m : moves){
            Stack<Character> temp = new Stack<>();
            for (int i = 0; i < m.count; i++) {
                var top = stacks.get(m.from).pop();
                temp.push(top);
            }
            while(!temp.isEmpty())
                stacks.get(m.to).push(temp.pop());
        }

        stacks.forEach(s -> System.out.print(s.peek()));
        System.out.println();
    }
}
