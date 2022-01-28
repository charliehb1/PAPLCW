package handlers;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class nodeManager implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String userIp = exchange.getRemoteAddress().getAddress().getHostAddress(); // get the user's IP address
        String sessionId = "USER"+userIp; // create a session id based on "USER" +  the user's IP address
        String query = exchange.getRequestURI().getQuery(); // get the query string
        String response = query.split("=")[1];
        web.webServerMgr.updateNode(sessionId, response); // update the nodemap
        JsonObject reply = web.webServerMgr.getNodeDesc(sessionId); // get the current node description
        exchange = setCORS(exchange); 
        exchange.sendResponseHeaders(200, 0);
        exchange.getResponseBody().write(reply.toString().getBytes());
        exchange.close();   
    }

    private HttpExchange setCORS(HttpExchange exchange) { // set the CORS headers this removes cross-origin errors when running both servies locally
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
        exchange.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        return exchange;
    }
}
