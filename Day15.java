import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Day15 extends AoC {
    Set<Entry> S;
    public Entry[] nums;

    public Day15() throws IOException {
        super(15);
    }

    record Entry(int x, int y, int d) {
    }

    public void init() {
        S = new HashSet<>();
        nums = new Entry[]{new Entry(-1, -1, 0), new Entry(-1, 1, 0), new Entry(1, -1, 0), new Entry(1, 1, 0)};
        for (var lines : input.split("\\n")) {
            var words = lines.split(" ");
            var sx = Integer.parseInt(words[2].split("=")[words[2].split("=").length - 1].split(",")[0]);
            var sy = Integer.parseInt(words[3].split("=")[words[3].split("=").length - 1].split(":")[0]);
            var bx = Integer.parseInt(words[8].split("=")[words[8].split("=").length - 1].split(",")[0]);
            var by = Integer.parseInt(words[9].trim().split("=")[words[9].split("=").length - 1]);
            S.add(new Entry(sx, sy, Math.abs(sx - bx) + Math.abs(sy - by)));
        }
    }

    public void part1() {

    }

    public void part2() {
        boolean part2 = false;
        for (var entry : S) {
            if (!part2)
                for (int dx = 0; dx < entry.d + 2; dx++) {
                    int dy = entry.d + 1 - dx;
                    for (var ee : nums) {
                        int x = entry.x + dx * ee.x;
                        int y = entry.y + dy * ee.y;
                        if (!(x >= 0 && x <= 4000000 && y >= 0 && y <= 4000000))
                            continue;
                        boolean found = true;
                        for (var eee : S) {
                            if (Math.abs(x - eee.x) + Math.abs(y - eee.y) <= eee.d)
                                found = false;
                        }
                        if (found) {
                            System.out.println((long)x * 4000000L + y);
                            part2 = true;
                        }
                    }
                }
        }
    }
}
