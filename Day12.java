import java.io.IOException;
import java.util.*;

public class Day12 extends AoC {
    public int[][] m;
    public char[][] ch;
    private static int R, C;
    private Deque<Pair> Q;

    public Day12() throws IOException {
        super(12);
    }

    record Point(int x, int y) {
        public Point add(Point p) {
            return new Point(p.x + x, p.y + y);
        }

        public boolean safe() {
            return x >= 0 && x < R && y >= 0 && y < C;
        }
    }

    record Pair(Point p, int v) {
    }

    public void init() {
        R = input.split("\\n").length;
        C = input.split("\\n")[0].trim().length();
        ch = new char[R][C];
        m = new int[R][C];
        Q = new ArrayDeque<>();
    }


    public void helper1(int part) {
        int count = 0;
        for (var s : input.split("\\n")) {
            m[count] = s.trim().chars().toArray();
            ch[count++] = s.trim().toCharArray();
        }
        for (int r = 0; r < R; r++)
            for (int c = 0; c < C; c++) {
                if (ch[r][c] == 'E')
                    m[r][c] = 122;
                if ((part == 1 && ch[r][c] == 'S') || part == 2 && ch[r][c] == 'a') {
                    m[r][c] = 97;
                    Q.addLast(new Pair(new Point(r, c), 0));
                }
            }
        Set<Point> S = new HashSet<>();
        while (!Q.isEmpty()) {
            Pair curr = Q.pollFirst();
            if (S.contains(curr.p))
                continue;
            S.add(curr.p);
            int r = curr.p.x, c = curr.p.y;
            if (ch[r][c] == 'E')
                System.out.println(curr.v);
            Point[] points = new Point[]{new Point(-1, 0), new Point(0, 1)
                    , new Point(1, 0), new Point(0, -1)};
            for (var temp : points) {
                Point newP = curr.p.add(temp);
                if (newP.safe() && m[newP.x][newP.y] <= m[r][c] + 1)
                    Q.addLast(new Pair(new Point(newP.x, newP.y), curr.v + 1));
            }
        }

    }

    public void part1() {
        helper1(1);
    }

    public void part2() {
        helper1(2);
    }
}