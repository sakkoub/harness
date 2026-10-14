package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class App {

    static final String VERSION = System.getenv().getOrDefault("APP_VERSION", "dev");

    public static String greet(String name) {
        if (name == null || name.isBlank()) {
            return "Hello, World!";
        }
        return "Hello, " + name + "!";
    }

    public static int add(int a, int b) {
        return a + b;
    }

    static String nameFromQuery(String query) {
        if (query == null) {
            return null;
        }
        for (String pair : query.split("&")) {
            if (pair.startsWith("name=")) {
                return URLDecoder.decode(pair.substring(5), StandardCharsets.UTF_8);
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/healthz", exchange -> respond(exchange, 200, "ok"));
        server.createContext("/", exchange -> {
            String name = nameFromQuery(exchange.getRequestURI().getRawQuery());
            respond(exchange, 200, greet(name) + " (version " + VERSION + ")");
        });
        server.start();
        System.out.println("Listening on port " + port + ", version " + VERSION);
    }

    private static void respond(com.sun.net.httpserver.HttpExchange exchange, int status, String body)
            throws IOException {
        byte[] bytes = (body + "\n").getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream out = exchange.getResponseBody()) {
            out.write(bytes);
        }
    }
}
