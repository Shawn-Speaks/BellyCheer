package backend;

import model.PantryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PantryService {
        @GET("api")
        Call<PantryResponse> getPantries(@Query("id") String id, @Query("sheet") String sheet, @Query("q") String q);
}
