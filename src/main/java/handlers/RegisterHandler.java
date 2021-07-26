package handlers;

import com.sun.net.httpserver.HttpExchange;
import data_access.DataAccessException;
import services.RegisterService;
import request.LoginRequest;
import request.RegisterRequest;
import results.RegisterResult;
import results.Result;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class RegisterHandler extends PostRequestHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
            RegisterService registerService = new RegisterService();
            try {
                RegisterRequest request = serializer.deserialize(convertInputStreamToString(exchange.getRequestBody()), RegisterRequest.class);
                RegisterResult result = registerService.register(request);
                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
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
