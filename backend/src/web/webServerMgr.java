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
            this.server = HttpServer.create(new InetSocketAddress(8000), 0);
            this.server.setExecutor(null);
        } catch (IOException e) {
            System.out.println("Error creating server");
        }
    }
    
    public void addHandler(String path, HttpHandler handler) {
        this.server.createContext(path, handler);
    }
    
    public void start() {
        this.server.start();
    }

    public static void createUserSession(String sessionId) {
        userSession[] sessions = getSessions();
        if(sessions == null) {
            userSession[] newSessions = new userSession[1];
            newSessions[0] = new userSession(sessionId);
            setSessions(newSessions);
            sessions = getSessions();
        } else {
            userSession[] newSessions = new userSession[sessions.length+1];
            for(int i = 0; i < sessions.length; i++) {
                newSessions[i] = sessions[i];
            }
            newSessions[sessions.length] = new userSession(sessionId);
            setSessions(newSessions);
            sessions = getSessions();
        }
    }

    public static JsonObject updateNode(String sessionId, String decision) {
        JsonObject jsonReply = null;
        userSession[] sessions = getSessions();
        for(int i = 0; i < sessions.length; i++) {
            if(sessions[i].getSessionId().equals(sessionId)) {
                sessions[i].updateNode(decision);
                jsonReply = sessions[i].getCurrentDescription();
            }
        }
        return jsonReply;
    }

    public static JsonObject getNodeDesc(String sessionId) {
        JsonObject reply = null;
        userSession[] sessions = getSessions();
        for(int i = 0; i < sessions.length; i++) {
            if(sessions[i].getSessionId().equals(sessionId)) {
                reply =  sessions[i].getCurrentDescription();
            }
        }
        return reply;
    }
}
