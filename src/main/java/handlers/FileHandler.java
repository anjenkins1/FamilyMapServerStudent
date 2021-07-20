package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class FileHandler implements HttpHandler {

    private static final String PAGE_NOT_FOUND_PATH = "web/HTML/404.html";
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().toUpperCase().equals("GET")) {
            String filePath;
            String urlPath = exchange.getRequestURI().toString();

            if (urlPath.equals("/")) {
                filePath = "web/index.html";
            }
            else {
                filePath = "web" + urlPath;
            }

            File file = new File(filePath);
            OutputStream responseBody = exchange.getResponseBody();
            if (file.exists()) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Files.copy(file.toPath(), responseBody);
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                File pageNotFoundFile = new File(PAGE_NOT_FOUND_PATH);
                Files.copy(pageNotFoundFile.toPath(), responseBody);
            }
            responseBody.close();
        }
        else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            exchange.getResponseBody().close();
        }
    }
}
