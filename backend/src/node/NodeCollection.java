package node;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

// import json file -> local lib folder for ease of access
import com.google.gson.Gson; 

import static java.lang.Integer.valueOf;

public class NodeCollection {

    private Node[] nodes;
    public Node locateNodeBy(int nodeID) {
        for (Node n : nodes) {
            if (nodeID == n.getID()) {
                return n;
            }
        }
        return new Node();
    }
    public Node get(int index){ return nodes[index]; }

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

    Node mapFields(String line) {
        String[] stringArray = line.split(",");
        Node n = new Node();
        n.setID(valueOf(stringArray[0]));
        n.setYesID(valueOf(stringArray[1]));
        n.setNoID(valueOf(stringArray[2]));
        n.setDescription(stringArray[4]);
        return n;
    }

    public int length() {
        return nodes.length;
    }
}
