package duplenskikh.simplejetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class JettyServer {
    private final int maxThreads = 100;
    private final int minThreads = 10;
    private final int idleTimeout = 120;
    private final QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);
    private Server server;

    public void start() throws Exception {
        server = new Server(threadPool);
        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(8080);
        server.setConnectors(new Connector[] {serverConnector});

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(GetServlet.class, "/index");
        servletHandler.addServletWithMapping(PostServlet.class, "/post-data");
        server.setHandler(servletHandler);

        server.start();
        server.join();
    }

    public void setHandler(ServletHandler servletHandler) {
        server.setHandler(servletHandler);
    }
}
