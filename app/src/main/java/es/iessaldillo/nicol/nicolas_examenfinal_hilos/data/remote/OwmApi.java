package es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.remote;

import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.remote.entities.CityWeatherDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OwmApi {
    @GET("weather")
    Call<CityWeatherDTO> getCityWeather(@Query("appid") String appid, @Query("q") String city, @Query("units") String units, @Query("lang") String language);

}
