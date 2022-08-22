package duplenskikh.crud;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import javax.servlet.http.HttpServlet;

public class CustomServer {
    private final int maxThreads = 100;
    private final int minThreads = 10;
    private final int idleTimeout = 120;
    private final QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);
    private final Server server;
    private final ServletContextHandler contextHandler;


    public CustomServer(int port) {
        this.server = new Server(threadPool);
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(port);
        server.setConnectors(new Connector[] {serverConnector});
        contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    }

    public void start() {
        try {
            server.join();
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addController(HttpServlet servlet, String pathSpec) {
        contextHandler.addServlet(new ServletHolder(servlet), pathSpec);
    }

    public void setHandler() {
        server.setHandler(contextHandler);
    }
}
