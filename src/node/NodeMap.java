package node;
public class NodeMap {

    private Node head;
    private Node currentNode;

    public Node currentNode() { return currentNode;}

    public void noDecision(){
        currentNode = currentNode.getYesNode();
    }

    public void decision(int decision) {
        switch (decision) {
            case 1:
                currentNode = currentNode.getYesNode();
                break;
            case 2:
                currentNode = currentNode.getNoNode();
                break;
        }
    }

    public NodeMap()  {
        NodeCollection nodeCollection;   
        nodeCollection = new NodeCollection();
        head = nodeCollection.get(0);
        buildMap(nodeCollection);
        currentNode = head;
    }

    private void buildMap(NodeCollection nodeCollection)   {
        if (nodeCollection == null) {return;}
        for(int i = 0; i < nodeCollection.length(); i++) {
            Node source = nodeCollection.get(i);
            int yesID = source.getYesID();
            int noID = source.getNoID();

            Node yesNode = nodeCollection.locateNodeBy(yesID);
            Node noNode = nodeCollection.locateNodeBy(noID);

            source.setYesNode(yesNode);
            source.setNoNode(noNode);
        }
    }
}