package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import data_access.DataAccessException;
import services.FillService;
import services.LoginService;
import services.request.LoginRequest;
import services.results.FillResult;
import services.results.LoginResult;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class FillHandler extends PostRequestHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
            String urlPath = exchange.getRequestURI().toString();

            String username = (urlPath.substring(6));

            int numGenerations;

            if (username.contains("/")) {
                numGenerations = Integer.parseInt(username.substring(username.indexOf("/") + 1));
                username = username.substring(0,username.indexOf("/"));
            }
            else {
                numGenerations = 4;
            }
            FillService fillService = new FillService(username, numGenerations);

            FillResult result = fillService.fill();
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
