package handlers;

import com.sun.net.httpserver.HttpExchange;
import services.PersonService;
import services.results.AllPersonsResult;
import services.results.Result;
import services.results.SinglePersonResult;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class PersonHandler extends AuthorizingRequestHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            OutputStream responseBody = exchange.getResponseBody();
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {

                String authToken = getAuthToken(exchange);

                String urlPath = exchange.getRequestURI().toString();
                String personID;

                PersonService personService = new PersonService();
                Result authResult = personService.checkAuthToken(authToken);

                if (authResult.isSuccess()) {
                    if (urlPath.substring(1).contains("/")) {
                        personID = urlPath.substring(urlPath.lastIndexOf("/") + 1);
                        SinglePersonResult result = personService.getOnePerson(personID);
                        if (result.isSuccess()) {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        }
                        else {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }
                        writeJsonResponse(result, responseBody);
                    }
                    else {
                        AllPersonsResult allPersonsResult = personService.getAllPeople();
                        if (allPersonsResult.isSuccess()) {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        }
                        else {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }
                        writeJsonResponse(allPersonsResult, responseBody);
                    }
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    writeJsonResponse(authResult, responseBody);
                }
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
            responseBody.close();
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
