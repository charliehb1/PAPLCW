import handlers.newUser;
import handlers.nodeManager;
import web.webServerMgr;

public class main {
    public static void main(String args[]) {
        webServerMgr webServer = new webServerMgr();
        webServer.addHandler("/", new newUser());
        webServer.addHandler("/nodeResult", new nodeManager());
        webServer.start();
        System.out.println("Server started and running: console will remain open until stopping the server...");
    }
}
