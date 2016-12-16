package lorem.lovel.network;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

import lorem.lovel.App;
import lorem.lovel.models.BigCountryResult;
import lorem.lovel.models.CardModel;

public class BigCountryService {

    private static final String COUNTRY_REQUEST_TAG = "big_country_request";

    public interface BigCountryListener {
        void onReceive(CardModel[] countries);
        void onFailed();
    }

    public static void getBigCountries(final BigCountryListener  listener) {

        String url = UrlBuilder.getCountriesUrl();

        //Create the request
        JacksonRequest<BigCountryResult> request = createBigCountriesRequest(url, listener);

        sendRequest(request);

    }

    public static void getBeers(String name, BigCountryListener  listener) {

        String url = UrlBuilder.getCountriesUrl(name);

        //Create the request
        JacksonRequest<BigCountryResult> request = createBigCountriesRequest(url, listener);

        // Before we send the request, first we cancel the previous request
        cancelRequest(COUNTRY_REQUEST_TAG);

        request.setTag(COUNTRY_REQUEST_TAG);
        sendRequest(request);
    }

    private static void cancelRequest(String countryRequestTag) {
        App.getSharedInstance()
                .getRequestQueue()
                .cancelAll(countryRequestTag);

    }

    private static JacksonRequest<BigCountryResult> createBigCountriesRequest(String url,
                                                                              final BigCountryListener  listener) {

        JacksonRequest<BigCountryResult> request =
                new JacksonRequest<>(Request.Method.GET, url, new JacksonRequestListener<BigCountryResult>() {
                    @Override
                    public void onResponse(BigCountryResult response, int statusCode, VolleyError error) {

                        //response !!!

                        if(response!=null) {
                            CardModel[] bigCountries = response.getData();

                            if(listener!=null) {
                                listener.onReceive (bigCountries);
                            }
                        }

                        if(error!=null) {
                            if(listener!=null) {
                                listener.onFailed();
                            }
                        }

                    }

                    @Override
                    public JavaType getReturnType() {
                        return SimpleType.construct(BigCountryResult.class);
                    }
                });

        return request;
    }


    private static void sendRequest(JacksonRequest request) {
        App.getSharedInstance()
                .getRequestQueue()
                .add(request);

    }

}
