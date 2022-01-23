package handlers;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class newUser implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String userIp = exchange.getRemoteAddress().getAddress().getHostAddress();
        String sessionId = "USER"+userIp;
        web.webServerMgr.createUserSession(sessionId);
        JsonObject reply = web.webServerMgr.getNodeDesc(sessionId);
        exchange = setCORS(exchange);
        exchange.sendResponseHeaders(200, reply.toString().length());
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