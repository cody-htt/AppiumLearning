package utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

public class JSONReader {

    private InputStream inputStream;

    public JSONObject readJSONFile(String filePath) {
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
            JSONTokener jsonTokener = new JSONTokener(inputStream);
            return new JSONObject(jsonTokener);
        } catch (Exception ignored) { } finally {
            if (inputStream != null) {
                try { inputStream.close(); } catch (IOException ex) { ex.printStackTrace(); }
            }
        }
        return null;
    }
}
