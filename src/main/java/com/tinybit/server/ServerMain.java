package com.tinybit.server;

import com.tinybit.constants.Constants;
import com.tinybit.scheduler.TinybitScheduler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerMain {

    public static void main(String args[]) throws Exception {
        GzipHandler gzipHandler = new GzipHandler();
        gzipHandler.setIncludedMimeTypes("text/html", "text/plain", "text/xml",
                "text/css", "application/json", "text/javascript");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setGzipHandler(gzipHandler);
        Server jettyServer = new Server(8082);
        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", Constants.RESOURCES_PACKAGE);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", "org.glassfish.jersey.jackson.JacksonFeature,org.glassfish.jersey.jackson.JacksonBinder");
        try {
            jettyServer.start();
            TinybitScheduler.begin(168);
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
