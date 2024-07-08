package com.example.weatherapi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.example.weatherapi.controller.WeatherController;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8086);
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.setContextPath("/");
        server.setHandler(handler);
        handler.addServlet(new ServletHolder(new WeatherController()), "/weather");

        server.start();
        server.join();
    }
}
