package web;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class webServerMgr {

    public HttpServer server;
    private static userSession[] userSessions;

    private static userSession[] getSessions() {
        return userSessions;
    }
    
    private static void setSessions(userSession[] newSessions) {
        userSessions = newSessions;
    }

    public webServerMgr() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(8000), 0); // Open a new socket connection on port 8000
            this.server.setExecutor(null); // Set the executor to null
        } catch (IOException e) {
            System.out.println("Error creating server");
        }
    }
    
    public void addHandler(String path, HttpHandler handler) {
        this.server.createContext(path, handler); // This ties the path with the handler
    }
    
    public void start() {
        this.server.start();
    }

    public static void createUserSession(String sessionId) {
        userSession[] sessions = getSessions();
        if(sessions == null) { // If no sessions currently exist then create a new array of sessions and add the user
            userSession[] newSessions = new userSession[1];
            newSessions[0] = new userSession(sessionId);
            setSessions(newSessions);
            sessions = getSessions(); // update local sessions variable
        } else {
            userSession[] newSessions = new userSession[sessions.length+1]; // clone and add a new user session
            for(int i = 0; i < sessions.length; i++) {
                newSessions[i] = sessions[i];
            }
            newSessions[sessions.length] = new userSession(sessionId);
            setSessions(newSessions);
            sessions = getSessions();
        }
    }

    public static JsonObject updateNode(String sessionId, String decision) { // get the current node description and update the nodemap
        JsonObject jsonReply = null;
        userSession[] sessions = getSessions();
        for(int i = 0; i < sessions.length; i++) {
            if(sessions[i].getSessionId().equals(sessionId)) {
                sessions[i].updateNode(decision); // update the users nodemap with the new decision
                jsonReply = sessions[i].getReplyToSend(); // get the current node description
            }
        }
        return jsonReply;
    }

    public static JsonObject getNodeDesc(String sessionId) { // get the current node description
        JsonObject reply = null;
        userSession[] sessions = getSessions();
        for(int i = 0; i < sessions.length; i++) {
            if(sessions[i].getSessionId().equals(sessionId)) {
                reply =  sessions[i].getReplyToSend();
            }
        }
        return reply;
    }
}
