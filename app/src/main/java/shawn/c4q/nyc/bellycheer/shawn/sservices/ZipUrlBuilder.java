package shawn.c4q.nyc.bellycheer.shawn.sservices;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shawn.c4q.nyc.bellycheer.shawn.smodels.ZipResponse;

/**
 * Created by shawnspeaks on 2/18/17.
 */

public class ZipUrlBuilder {

    private static final String zipBaseUrl = "http://api.geonames.org/";
    private static ZipUrlBuilder instance;
    private static NearbyZipService service;
    private Map<String, String> queryParams = new HashMap<>();

public static ZipUrlBuilder getInstance(){
        if(instance == null){
            instance = new ZipUrlBuilder();
        }
        return instance;
    }

    public void addToMap(String key, String value){
        queryParams.put(key, value);
    }

    private ZipUrlBuilder(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(zipBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(NearbyZipService.class);
    }

    public Call<ZipResponse> listJSON(){
        return service.listJSON(queryParams);
    }


}
