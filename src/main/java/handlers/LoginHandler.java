package handlers;

import com.sun.net.httpserver.HttpExchange;
import data_access.DataAccessException;
import services.LoginService;
import request.LoadRequest;
import request.LoginRequest;
import results.LoadResult;
import results.LoginResult;
import results.Result;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoginHandler extends PostRequestHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
            LoginService loginService = new LoginService();
            try{
                LoginRequest request = serializer.deserialize(convertInputStreamToString(exchange.getRequestBody()), LoginRequest.class);
                LoginResult result = loginService.login(request);

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
