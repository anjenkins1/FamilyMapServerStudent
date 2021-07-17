package handlers;

import com.sun.net.httpserver.HttpExchange;
import data_access.DataAccessException;
import services.ClearService;
import services.LoadService;
import services.request.LoadRequest;
import services.request.LoginRequest;
import services.results.LoadResult;
import services.results.Result;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoadHandler extends PostRequestHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
            try {
                LoadService loadService = new LoadService();
                LoadRequest request = serializer.deserialize(convertInputStreamToString(exchange.getRequestBody()), LoadRequest.class);
                LoadResult result = loadService.load(request);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_ACCEPTED, 0);
                OutputStream responseBody = exchange.getResponseBody();
                writeJsonResponse(result, responseBody);
                responseBody.close();
            } catch(DataAccessException e) {
                e.printStackTrace();
            }
        }
        else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
        }
    }
}
