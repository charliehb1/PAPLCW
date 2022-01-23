package web;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

public class webServerNew {

    public HttpServer server;

    private static userSession[] userSessions;
    private static userSession[] getSessions() {
        return userSessions;
    }
    private static void setSessions(userSession[] userSessions) {
        userSessions = userSessions;
    }

    public webServerNew() {
        try {
            this.server = HttpServer.create(new InetSocketAddress(8000), 0);
            this.server.setExecutor(null);
        } catch (IOException e) {
            System.out.println("Error creating server");
        }
    }
    
    public void addHandler(String path, HttpHandler handler) {
        System.out.println(this.server);
        
        this.server.createContext(path, handler);
    }
    
    public void start() {
        this.server.start();
    }

    public static void createUserSession(String sessionId) {
        System.out.println("Creating new user session: "+sessionId);
        
        userSession[] sessions = getSessions();
        userSession[] newSessions = new userSession[sessions.length+1];
        for(int i = 0; i < sessions.length; i++) {
            newSessions[i] = sessions[i];
        }
        newSessions[sessions.length] = new userSession(sessionId);
        setSessions(newSessions);
        System.out.println("Created new user session: "+sessionId);
    }
}
