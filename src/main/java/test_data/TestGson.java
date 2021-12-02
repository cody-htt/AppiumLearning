package test_data;

import com.google.gson.Gson;

public class TestGson {

    public static void main(String[] args) {
        // Convert JSON to object
        String jsonObject = "{\n" +
                            "  \"validCredentials\": {\n" +
                            "    \"email\": \"Tunghuynh@gmail.com\",\n" +
                            "    \"password\": \"12345678\"\n" +
                            "  }}";

        Gson gson = new Gson();
        LoginCreds loginCreds = gson.fromJson(jsonObject, LoginCreds.class);
        System.out.println(loginCreds);

        System.out.println(gson.toJson(loginCreds));
    }
}
