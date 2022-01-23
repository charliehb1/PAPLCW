import java.io.FileNotFoundException;

import node.Console;
import node.NodeMap;
public class ConsoleTest {
    public static void main(String[] args) throws FileNotFoundException {
        NodeMap map = new NodeMap();
        Console c = new Console(map);
    }
}
