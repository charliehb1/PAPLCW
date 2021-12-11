import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.SocketHandler;

public class WebServerTest {

    public void main() throws IOException  {
        ServerSocket socket = new ServerSocket(8080);
        System.out.println("Listening on port " + 8080);
        Socket client;
        while ((client = socket.accept()) != null)  {
          System.out.println("Received connection from " + client.getRemoteSocketAddress().toString());
          //SocketHandler handler = new SocketHandler(client, handlers);
          //Thread t = new Thread(handler);
          //t.start();
        }
    }
}