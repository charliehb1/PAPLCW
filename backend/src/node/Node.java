package node;
import com.google.gson.annotations.SerializedName;

public class Node {
    @SerializedName("Id")
    private int ID;
    @SerializedName("Yes")
    private int yesID;
    @SerializedName("No")
    private int noID;
    @SerializedName("Description")
    private String description;
    @SerializedName("isQuestion")
    private String isQuestion;
    private Node yesNode;
    private Node noNode;

    public Node(int ID, int yesID, int noID, String description, String isQuestion) {
        this.ID = ID;
        this.yesID = yesID;
        this.noID = noID;
        this.description = description;
        this.isQuestion = isQuestion;
    }

    public Node() {}

    public int getID() {return ID;}
    public void setID(int ID) {this.ID = ID;}
    public int getYesID() {return yesID;}
    public void setYesID(int yesID) {this.yesID = yesID; }
    public int getNoID() {return noID;}
    public void setNoID(int noID) {this.noID = noID;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description; }
    public Node getYesNode() {return yesNode;}
    public void setYesNode(Node yes) {this.yesNode = yes;}
    public Node getNoNode() {return noNode;}
    public void setNoNode(Node no) {this.noNode = no;}
    public String isQuestion() {return this.isQuestion;}
    public void setIsQuestion(String isQuestion) {this.isQuestion = isQuestion;}

    @Override
    public String toString() {
        return "nodeID:" + ID +
                ", yesID:" + yesID +
                ", noID:" + noID +
                ", description:'" + description +
                ", isQuestion:'" + isQuestion;
    }
}
