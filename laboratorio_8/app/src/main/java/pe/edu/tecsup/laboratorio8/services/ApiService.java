package pe.edu.tecsup.laboratorio8.services;

import pe.edu.tecsup.laboratorio8.models.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("nearbysearch/json")
    Call<Map> getDataMarkers(@Query("location")String location,
                             @Query("radius")String radius,
                             @Query("type")String type,
                             @Query("keyword")String keyword,
                             @Query("key")String key);
}
