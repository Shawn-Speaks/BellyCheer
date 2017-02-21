package shawn.c4q.nyc.bellycheer.shawn.sservices;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shawn.c4q.nyc.bellycheer.shawn.smodels.GeoLocationResponse;

import static android.content.ContentValues.TAG;

/**
 * Created by shawnspeaks on 2/18/17.
 */

public class AddressToCoordRetrofit {

    private String address;
    private AddressUrlBuilder mAddressUrlBuilder;
    private float lat;
    private float lng;

    public AddressToCoordRetrofit(String address) {
        this.address = address;

        String GEO_KEY = "AIzaSyB_S1DeH4S4g1B7F-1FdSvhhC-WbI0bs7Q";
        mAddressUrlBuilder = AddressUrlBuilder.getInstance();
        mAddressUrlBuilder.addToMap("key", GEO_KEY);
        mAddressUrlBuilder.addToMap("address", address);

        Call<GeoLocationResponse> call = mAddressUrlBuilder.listJSON();
        call.enqueue(new Callback<GeoLocationResponse>() {
            @Override
            public void onResponse(Call<GeoLocationResponse> call, Response<GeoLocationResponse> response) {
                Log.d(TAG, "Success!");
                GeoLocationResponse geoLocationResponse = response.body();
                lat = geoLocationResponse.getResultsArrayList().get(0).getGeometry().getLocation().getLat();
                lng = geoLocationResponse.getResultsArrayList().get(0).getGeometry().getLocation().getLng();
                Log.d(TAG, String.valueOf(lat));
                Log.d(TAG, String.valueOf(lng));


            }

            @Override
            public void onFailure(Call<GeoLocationResponse> call, Throwable t) {
                Log.d(TAG, "Failure");
            }
        });

    }
}
