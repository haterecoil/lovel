package lorem.lovel.network;

/**
 * Created by Yvan Moté on 22/11/2016.
 *
 */

public class UrlBuilder {

    private static final String BASE_URL = "http://api.brewerydb.com/";

    private static final String API_KEY = "7823b85cb7c94a021b78b886f4a8f1d1";

    public static String getCountriesUrl() {
        return BASE_URL+"/cities.json";
    }

    public static String getCountriesUrl(String name) {
        return BASE_URL+"/cities.json&name="+name;
    }
}
