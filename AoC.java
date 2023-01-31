import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public abstract class AoC {
    protected final java.nio.file.Path fileName;
    protected final String input;
    public AoC(int index) throws IOException {
        String path = "input/day_"
                + (index<10 ? "0" + index : index)
                + ".txt";
        fileName = Path.of(path);


        input = Files.readString(fileName);

        init();
        part1();
        part2();
    }
    public abstract void init();
    public abstract void part1();

    public abstract void part2();
}
