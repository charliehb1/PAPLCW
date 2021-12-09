import java.io.FileNotFoundException;

public class TestFlatFileDB {
    static NodeCollection nodeCollection;
    public static void main(String[] args) {
        System.out.println("FlatFile");
        nodeCollection = new NodeCollection();
        print(nodeCollection.toString());
    }

    public static void print(String  info){System.out.println(info);}

}
