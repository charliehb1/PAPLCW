package handlers;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class nodeManager implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String userIp = exchange.getRemoteAddress().getAddress().getHostAddress();
        String sessionId = "USER"+userIp;
        System.out.println(exchange.getRequestURI().getQuery());
        String query = exchange.getRequestURI().getQuery();
        String response = query.split("=")[1];
        web.webServerMgr.updateNode(sessionId, response);
        JsonObject reply = web.webServerMgr.getNodeDesc(sessionId);
        exchange = setCORS(exchange);
        exchange.sendResponseHeaders(200, 0);
        exchange.getResponseBody().write(reply.toString().getBytes());
        exchange.close();   
    }

    private HttpExchange setCORS(HttpExchange exchange) {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
        exchange.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        return exchange;
    }
}
