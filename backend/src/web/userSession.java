package web;
import com.google.gson.JsonObject;

import node.NodeMap;

public class userSession {

    private String id;
    public NodeMap map;
    public userSession(String userId) {
        id = userId;
        map = new NodeMap();
    }

    public JsonObject updateNode(String reply) {
        int dec = 0;
        if(reply.equals("Yes")) {
            dec = 1;
        }
        if(reply.equals("No")) {
            dec = 2;
        }
        map.decision(dec);
        JsonObject jsonReply = new JsonObject();
        jsonReply.addProperty("question", map.currentNode().getDescription());
        return jsonReply;
    }

    public JsonObject getCurrentDescription() {
        JsonObject jsonReply = new JsonObject();
        jsonReply.addProperty("question", map.currentNode().getDescription());
        return jsonReply;
    }

    public String getSessionId() {
        return id;
    }
    public void getSessionId(String id) {
        this.id = id;
    }

}
