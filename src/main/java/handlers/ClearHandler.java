package handlers;

import com.sun.net.httpserver.HttpExchange;
import services.ClearService;
import services.results.Result;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class ClearHandler extends PostRequestHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
            ClearService clearService = new ClearService();
            Result result = clearService.clear();
            if (result.isSuccess()) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
            OutputStream responseBody = exchange.getResponseBody();
            writeJsonResponse(result, responseBody);
            responseBody.close();
        }
        else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
        }
    }
}
