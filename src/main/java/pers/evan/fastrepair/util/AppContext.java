package pers.evan.fastrepair.util;

/**
 * Created by Evan on 2016/1/17.
 */
public class AppContext {

    static String baseUrl = "http://localhost:8081";

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        AppContext.baseUrl = baseUrl;
    }
}
