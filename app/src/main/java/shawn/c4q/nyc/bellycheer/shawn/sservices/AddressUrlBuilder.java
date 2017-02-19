package shawn.c4q.nyc.bellycheer.shawn.sservices;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shawn.c4q.nyc.bellycheer.shawn.smodels.GeoLocationResponse;

/**
 * Created by shawnspeaks on 2/18/17.
 */

public class AddressUrlBuilder {

    private static final String geoLocationUrl = "https://maps.googleapis.com/";
    private static AddressUrlBuilder instance;
    private static GoogleGeoLocationService service;
    private Map<String, String> queryParams = new HashMap<>();

    public static AddressUrlBuilder getInstance(){
        if(instance == null){
            instance = new AddressUrlBuilder();
        }
        return instance;
    }

    public void addToMap(String key, String value){
        queryParams.put(key, value);
    }

    private AddressUrlBuilder(){
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(geoLocationUrl)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        service = retrofit.create(GoogleGeoLocationService.class);
    }

    public Call<GeoLocationResponse> listJSON(){
        return service.listJSON(queryParams);
    }


}
