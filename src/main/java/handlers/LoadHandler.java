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
            LoadService loadService = new LoadService();
            LoadRequest request = (LoadRequest) getObjectFromInputStream(exchange.getRequestBody(), new LoadRequest());
            System.out.println(request.toString());
            try {
                LoadResult result = loadService.load(request);
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        else {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
        }
    }
}
