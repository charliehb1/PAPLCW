import java.io.FileNotFoundException;
public class FlatFileDBTest {
    static NodeCollection nodeCollection;
    public static void main(String[] args) {
        nodeCollection = new NodeCollection();
        print(nodeCollection.toString());
    }
    public static void print(String  info){System.out.println(info);}
}