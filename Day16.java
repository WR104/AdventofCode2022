import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day16 extends AoC{
    Map<String, Valve> valves;
    public Day16() throws IOException {
        super(16);
    }
    public record Valve(String name, long flow, List<String> others) {}
    public record State(Map<String, Long> open, Valve valve, long totalFlow) {}
    public record State2(Map<String, Long> open, Valve me, Valve elephant, long totalFlow) {}

    private Valve read(String string){
        var str = string.split(" ");
        var name = str[1];
        var flow = Long.parseLong(str[4].trim().split("=")[1].split(";")[0]);
        var others = Arrays.stream(
                string.trim()
                        .split(str.length > 10? "valves" : "valve")[1]
                        .trim().split(","))
                .map(s -> s = s.trim())
                .toList();
        return new Valve(name, flow, others);
    }
    private Map<String, Valve> input(){
        return input.lines()
                .map(this::read)
                .collect(Collectors.toMap(v -> v.name, v -> v));
    }

    public void init() {
        valves = input();
        Set<State> states = new HashSet<>();
        states.add(new State(new HashMap<>(), valves.get("AA"), 0));
        for(int minutes = 0; minutes<30; minutes++) {
            Set<State> newStates = new HashSet<>();
            for(State s : states) {
                long flow = s.open.values().stream().mapToLong(e -> e).sum() + s.totalFlow;
                if(s.valve.flow > 0 && !s.open.containsKey(s.valve.name)) {
                    Map<String, Long> newOpen = new HashMap<>(s.open);
                    newOpen.put(s.valve.name, s.valve.flow);
                    newStates.add(new State(newOpen, s.valve, flow));
                }
                s.valve.others.forEach(name -> newStates.add(new State(s.open, valves.get(name), flow)));
            }
            states = newStates;
            System.out.println("Minute: " + minutes);
        }
        var res = states.stream()
                .mapToLong(State::totalFlow)
                .max()
                .getAsLong();
        System.out.println(res);
    }

    public void part1() {

    }

    public void part2() {
        var valves = input();
        var openable = valves.values().stream()
                .filter(s -> s.flow > 0)
                .map(Valve::name)
                .collect(Collectors.toSet());
    }
}
