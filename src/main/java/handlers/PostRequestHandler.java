package handlers;

import java.io.InputStream;
import java.util.Scanner;

public class PostRequestHandler extends RequestHandler {

    protected Object getObjectFromInputStream(InputStream stream, Object o) {
        return serializer.deserialize(convertInputStreamToString(stream), o.getClass());
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
