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
    // This function collects a json file and converts them into the nodeMap
    public NodeCollection() {
        String path = "../backend/map.json"; // path to the json file
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(path)); // read the file
        } catch (FileNotFoundException e) {
            bufferedReader = localPath(); // If the file doesn't exist then use the local path (the path is depenandent on how the class is ran)
        }
        try {
            Gson gson = new Gson(); // Googles JSON reader the jar located in the libs folder
            Node[] tempNodes = gson.fromJson(bufferedReader, Node[].class);
            nodes = tempNodes;
        }
        catch (Exception e) {
            System.out.println("File cannot be found, error: " + e); // This is used if the file cannot be found at all.
        }
    }

    private BufferedReader localPath() {
        BufferedReader temp = null;
        try {
            temp =  new BufferedReader(new FileReader("./backend/map.json")); // If the program is ran from vscode then use the local path
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
    Node mapFields(String line) {
        String[] stringArray = line.split(",");
        Node n = new Node();
        n.setID(valueOf(stringArray[0]));
        n.setYesID(valueOf(stringArray[1]));
        n.setNoID(valueOf(stringArray[2]));
        n.setDescription(stringArray[4]);
        n.setIsQuestion(stringArray[5]);
        return n;
    }

    public int length() {
        return nodes.length;
    }
}
