import java.util.Scanner;

import node.NodeMap;

@SuppressWarnings("ALL")
public class Console {
    Scanner io;
    public Console(NodeMap map){
        io = new Scanner(System.in);
        while (map.currentNode() != null) {
            print(map.currentNode().getDescription());
            map.decision(fromConsoleGetInt("Yes or No? (press 1 for Yes or 2 No)"));
        }
    }

    public  int fromConsoleGetInt(String prompt){
        print(prompt);
        int retVal = io.nextInt();
        return retVal;
    }

    public  void print(String  info){System.out.println(info);}
    public  void lineBreak(){
        System.out.println("\n---------------");
    }

}
