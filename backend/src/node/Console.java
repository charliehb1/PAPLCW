package node;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Console {
    Scanner io;
    Random random = new Random();
    public Console(NodeMap map){
        io = new Scanner(System.in);
        while (map.currentNode() != null) {
            print(map.currentNode().getDescription());
            if(map.currentNode().getDescription().equals("You have reached the end of the decision map.")){ // purely for testing purposes the user
                // will be able to restart the map button in the front end which regenerates the user session which I will go into more detail on later
                break;
            }
            if(map.currentNode().isQuestion().equals("true")) { // Check if a question
                map.decision(fromConsoleGetInt("Yes or No? (press 1 for Yes or 2 No)")); // Get user input
            }
            else {
                System.out.println("Not a question, continuing without input...");
                print("-------------------------");
                int retVal = random.nextInt(1);
                retVal = retVal + 1; // Add 1 to the random number to make it 1 or 2
                map.decision(retVal); // Map still requires a decision to continue but these both lead to the same node, this is disguised to the user.
            }
        }
    }

    public  int fromConsoleGetInt(String prompt){
        print(prompt);
        //int retVal = io.nextInt();
        int retVal = 0; // Set to 0 but is updated by the random number
        io.next(); // await input so we can step through the process
        retVal = random.nextInt(2); // Generate a random number between 0 and 1
        retVal = retVal + 1; // Add 1 to the random number to make it 1 or 2
        print("Random number: "+retVal);
        print("-------------------------");
        return retVal;
    }

    public  void print(String  info){System.out.println(info);}
    public  void lineBreak(){
        System.out.println("\n---------------");
    }
}