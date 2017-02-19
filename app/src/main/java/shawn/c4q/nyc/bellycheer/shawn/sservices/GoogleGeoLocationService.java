package shawn.c4q.nyc.bellycheer.shawn.sservices;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import shawn.c4q.nyc.bellycheer.shawn.smodels.GeoLocationResponse;

/**
 * Created by shawnspeaks on 2/18/17.
 */

public interface GoogleGeoLocationService {

    @GET("/maps/api/geocode/json")
    Call<GeoLocationResponse> listJSON(@QueryMap Map<String, String> queryMap);

}
