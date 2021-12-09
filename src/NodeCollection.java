//import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


// import json file -> local lib folder for ease of access
import com.google.gson.Gson; 


import static java.lang.Integer.valueOf;

public class NodeCollection {

    private Node[] nodes;
/****************************************************/
/*************    SUPPORT MAP BUILDER  **************/
/****************************************************/
/****************************************************/
    public Node locateNodeBy(int nodeID) {
        for (Node n : nodes) {
            if (nodeID == n.getID()) {
                return n;
            }
        }
        return new Node();
    }
    public ArrayList<Node> arrayList(){
        return nodes;
    }
    public Node get(int index){ return nodes[index]; }
/****************************************************/
/**************         BUILD      ******************/
/****************************************************/
/****************************************************/


/**
 * @throws FileNotFoundException**************************************************/ 
//NODE COLLECTION REWRITE
/*
    public NodeCollection()  throws FileNotFoundException {
        java.io.File prc = new java.io.File("src/dataCorrected2.csv");
        Scanner fileRef = new Scanner(prc);
        nodes = new ArrayList<Node>();
        Node node;
        while (fileRef.hasNext()) {
            String line = fileRef.nextLine();
            node = mapFields(line);
            nodes.add(node);
        }
        fileRef.close();
    }
*/

    public NodeCollection() {
        String path = "C:/Users/charl/Desktop/PAPL/map.json";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
            Gson gson = new Gson();
            Node[] tempNodes = gson.fromJson(bufferedReader, Node[].class);
            nodes = tempNodes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    //private @NotNull
    Node mapFields(String line) {
        String[] stringArray = line.split(",");
        Node n = new Node();
        n.setID(valueOf(stringArray[0]));
        n.setYesID(valueOf(stringArray[1]));
        n.setNoID(valueOf(stringArray[2]));
        n.setDescription(stringArray[4]);
        return n;
    }

    public String toString(){
        String str = "";
        for(Node n : nodes){
            str += n.toString() + "\n";
        }
        return str;
    }
    public int length() {
        return nodes.length;
    }
}
