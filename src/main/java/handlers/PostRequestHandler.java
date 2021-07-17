package handlers;

import java.io.InputStream;
import java.util.Scanner;

public class PostRequestHandler extends RequestHandler {

    protected <T> T getObjectFromInputStream(InputStream stream, Class<T> o) {
        return serializer.deserialize(convertInputStreamToString(stream), o);
    }

    public String convertInputStreamToString(InputStream stream) {
        Scanner scanner = new Scanner(stream).useDelimiter("\\A");
        StringBuilder output = new StringBuilder();
        while (scanner.hasNext()) {
            output.append(scanner.next());
        }
        return output.toString();
    }

}
