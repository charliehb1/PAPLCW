package handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import web.webServerNew;


public class newUser implements HttpHandler {
    public void handle(HttpExchange exc) throws IOException {
        //Generate new user session name
        String userIp = exc.getRemoteAddress().getAddress().getHostAddress();
        String sessionId = "USER"+userIp;

        //Add new user session to array
        System.out.println("Attempting to create new user session: "+sessionId);
        web.webServerNew.createUserSession(sessionId);
    }
}