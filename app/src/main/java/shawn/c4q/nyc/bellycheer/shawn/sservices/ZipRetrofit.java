package shawn.c4q.nyc.bellycheer.shawn.sservices;

import android.location.Location;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shawn.c4q.nyc.bellycheer.shawn.smodels.PostalCodes;
import shawn.c4q.nyc.bellycheer.shawn.smodels.ZipResponse;

import static android.content.ContentValues.TAG;

/**
 * Created by shawnspeaks on 2/18/17.
 */

public class ZipRetrofit {

    private ZipUrlBuilder mZipUrlBuilder;

    public ZipRetrofit(Location mLocation, final ZipRetrofitListener zl) {
        mZipUrlBuilder = ZipUrlBuilder.getInstance();

        mZipUrlBuilder.addToMap("lat", String.valueOf(mLocation.getLatitude()));
        mZipUrlBuilder.addToMap("lng", String.valueOf(mLocation.getLongitude()));
        mZipUrlBuilder.addToMap("username", "shawnspeaks");

        Call<ZipResponse> call = mZipUrlBuilder.listJSON();
        call.enqueue(new Callback<ZipResponse>() {
            @Override
            public void onResponse(Call<ZipResponse> call, Response<ZipResponse> response) {
                Log.d(TAG, "success");
                ZipResponse zipResponse = response.body();
                ArrayList<PostalCodes> zipObjectList = zipResponse.getPostalCodesList();
                Log.d(TAG, "Current zipCode is " + zipObjectList.get(0).getPostalCode());
                zl.successfulCall(zipObjectList.get(0).getPostalCode());
            }

            @Override
            public void onFailure(Call<ZipResponse> call, Throwable t) {
                Log.d(TAG, "failure");
            }
        });

    }

    public interface ZipRetrofitListener{
        void successfulCall(String zip);
    }



}
