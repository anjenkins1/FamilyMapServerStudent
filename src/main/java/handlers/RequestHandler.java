package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class RequestHandler implements HttpHandler {

    protected JsonSerializer serializer;

    protected RequestHandler() {
        serializer = new JsonSerializer();
    }

    protected void writeJsonResponse(Object o, OutputStream stream) {
        try {
            serializer.serialize(o, stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {}
}
