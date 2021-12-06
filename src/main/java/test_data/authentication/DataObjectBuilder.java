package test_data.authentication;

import com.google.gson.Gson;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {

    public static <T> T buildDataObject(String filePath, Class<T> dataType) {
        LoginCreds[] credInformations = new LoginCreds[] { };
        String absoluteFilePath = System.getProperty("user.dir") + File.separator + filePath;
        try (
                Reader jsonReader = Files.newBufferedReader(Paths.get(absoluteFilePath))
        ) {
            //Create Gson instance
            Gson gson = new Gson();
            //Convert json array data into loginCreds
            return gson.fromJson(jsonReader, dataType);
        } catch (Exception ex) { ex.printStackTrace(); }

        return null;
    }

}
