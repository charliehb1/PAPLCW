import handlers.newUser;
import web.webServerNew;

import java.io.IOException;

import com.sun.net.httpserver.*;

public class main {
    public static void main(String args[]) {
        // Create a new web server
        webServerNew webServer = new webServerNew();
        // Add handlers
        webServer.addHandler("/", new newUser());
        // Start server
        webServer.start();
    }
}
