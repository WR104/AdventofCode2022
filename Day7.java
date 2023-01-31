import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day7 extends AoC{
    Node root;
    public Day7() throws IOException {
        super(7);
    }

    public void init() {
        root = parseTerminal();
    }

    record Node(int size, Node parent, Map<String,Node> nodes){
        static Pattern filePattern = Pattern.compile("(\\d+) ([\\w.]+)");

        Node apply(String line) {
            if (line.startsWith("$ cd ")) {
                return cd(line.substring(5));
            }
            var fileMatcher = filePattern.matcher(line);
            if (fileMatcher.matches()) {
                return add(fileMatcher.group(2), fileMatcher.group(1));
            }
            return this;
        }

        Node cd(String name) {
            return switch (name) {
                case "/" -> parent == null ? this : parent.cd("/");
                case ".." -> parent;
                default -> nodes.computeIfAbsent(name, k -> new Node(0, this, new TreeMap<>()));
            };
        }

        Node add(String name, String size) {
            nodes.put(name, new Node(Integer.parseInt(size), this, new TreeMap<>()));
            return this;
        }

        Stream<Node> dirs() {
            return Stream.concat(Stream.of(this), nodes.values().stream().flatMap(n -> n.dirs())
                    .filter(n -> n.size == 0));
        }

        public int size() {
            return size > 0 ? size : nodes.values().stream().mapToInt(n -> n.size()).sum();
        }
    }

    public Node parseTerminal(){
        Node root = new Node(0, null, new TreeMap<>());
        input.lines().reduce(root, (n, l) -> n.apply(l), (a, b) -> {return null;});
        return root;
    }
    public void part1() {
        var res = root.dirs()
                .filter(node -> node.size() <= 100000)
                .mapToInt(Node::size).sum();
        System.out.println(res);
    }
    public int helper1(String str){
        return str.lines()
                .filter(s -> s.split(" ").length > 1)
                .mapToInt(s -> Integer.parseInt(s.split(" ")[0]))
                .sum();
    }

    public void part2() {
        var needed = 30000000 - (70000000 - root.size());
        var res = root.dirs()
                .filter(node -> node.size() >= needed)
                .mapToInt(Node::size)
                .sorted()
                .findFirst()
                .getAsInt();
        System.out.println(res);
    }
}
//https://github.com/pvainio/adventofcode/blob/main/2022/java/Day7.java