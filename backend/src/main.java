import handlers.newUser;
import handlers.nodeManager;
import web.webServerMgr;

public class main {
    public static void main(String args[]) {
        webServerMgr webServer = new webServerMgr(); // Create a new instance of the custom web server
        webServer.addHandler("/", new newUser()); // Add a new path and handler
        webServer.addHandler("/nodeResult", new nodeManager()); // Add a new path and handler
        webServer.start(); // start the server
        System.out.println("Server started and running: console will remain open until stopping the server...");
    }
}
