import java.io.IOException;
import java.net.InetSocketAddress;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.*;

public class WebServer {
  public static userSession[] sessions;
  public static userSession[] getSessions() {
    return sessions;
  }
  public static void setSessions(userSession[] newSessions) {
    sessions = newSessions;
  }

  public static void main(String[] args) throws IOException  {
      
      HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
      server.createContext("/", new newUserHandler());
      server.createContext("/test", new testHandler());
      server.createContext("/nextNode", new nodeHandler());
      server.setExecutor(null);
      server.start();
  }

  static class newUserHandler implements HttpHandler {
    public void handle(HttpExchange exc) throws IOException {

      //Generate new user session data
      String userIp = exc.getRemoteAddress().getAddress().getHostAddress();
      String sessionId = "user"+userIp;

      addNewUser(sessionId); //Add new user session to array
      

      //find the users inital nodes

      System.out.println("Responding to: "+sessionId);
      JsonObject jsonReply = findUserSession(sessionId).getCurrentDescription();
      exc.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
      exc.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
      exc.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
      exc.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
      exc.getResponseHeaders().set("Content-Type", "application/json");
      exc.sendResponseHeaders(200, 0); //Reply with 200 status code & first node info
      exc.getResponseBody().write(jsonReply.toString().getBytes());
      exc.close();
    }

    private void addNewUser(String sessionId) {
      userSession[] temp = getSessions();

      if(temp == null) {
        // If no users currently exist, create new array
        userSession[] newSessions = new userSession[1];
        newSessions[0] = new userSession(sessionId);
        setSessions(newSessions);
        temp = getSessions();
      } 
      else {
        // If users already exist, create new array with new user
        userSession[] newSessions = new userSession[temp.length+1];
        // Copy data from old array to new array
        for(int i = 0; i < temp.length; i++) {
          newSessions[i] = temp[i];
        }
        newSessions[temp.length] = new userSession(sessionId);
        setSessions(newSessions);
        temp = getSessions();
      }
      // print data for testing purposes only
      for (int i = 0; i < temp.length; i++) {
        System.out.println(temp[i].getSessionId());
      }
    }

    private userSession findUserSession(String sessionId) {
      userSession[] sessions = getSessions();
      for (int i = 0; i < sessions.length; i++) {
        if(sessions[i].getSessionId().equals(sessionId)) {
          return sessions[i];
        }
      }
      return null;
    }
  }

  static class nodeHandler implements HttpHandler {
    public void handle(HttpExchange data) throws IOException {
        System.out.println("Node Handler ran");
        String session = "user"+data.getRemoteAddress().getAddress().getHostAddress();
        String[] query = data.getRequestURI().getQuery().split("&");
        String response = query[0].split("=")[1];
        System.out.println("response: "+response);
        JsonObject jsonReply = null;
        data.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        data.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        data.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
        data.getResponseHeaders().set("Access-Control-Allow-Credentials", "true");
        data.getResponseHeaders().set("Content-Type", "application/json");
        //Find user sessions
        userSession[] sessions = getSessions();
        System.out.println(sessions[0]);
        for (int i = 0; i < sessions.length; i++) {
          if(sessions[i].getSessionId().equals(session)) {
            System.out.println("User found");
            jsonReply = sessions[i].sendReply(response);
          }
        }
        data.sendResponseHeaders(200, 0);
        data.getResponseBody().write(jsonReply.toString().getBytes());
        data.close();
    }
  }

  static class testHandler implements HttpHandler {
    public void handle(HttpExchange res) throws IOException {
      String response = "This is a test response to validate server status";
      res.sendResponseHeaders(200, response.length());
      res.getResponseBody().write(response.getBytes());
      res.close();
    }
  }
}