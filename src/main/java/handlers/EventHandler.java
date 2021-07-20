package handlers;

import com.sun.net.httpserver.HttpExchange;
import services.EventService;
import services.PersonService;
import services.results.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class EventHandler extends AuthorizingRequestHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            OutputStream responseBody = exchange.getResponseBody();
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {

                String authToken = getAuthToken(exchange);

                String urlPath = exchange.getRequestURI().toString();
                String eventID;

                EventService eventService = new EventService();
                Result authResult = eventService.checkAuthToken(authToken);

                if (authResult.isSuccess()) {
                    if (urlPath.substring(1).contains("/")) {
                        eventID = urlPath.substring(urlPath.lastIndexOf("/") + 1);
                        SingleEventResult result = eventService.getOneEvent(eventID);
                        if (result.isSuccess()) {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        }
                        else {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }
                        writeJsonResponse(result, responseBody);
                    }
                    else {
                        AllEventResult allEventResult = eventService.getAllEvents();
                        if (allEventResult.isSuccess()) {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        }
                        else {
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }
                        writeJsonResponse(allEventResult, responseBody);
                    }
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    writeJsonResponse(authResult, responseBody);
                }
            }
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
            }
            responseBody.close();
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
