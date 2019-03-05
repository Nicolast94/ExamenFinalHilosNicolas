package es.iessaldillo.nicol.nicolas_examenfinal_hilos.data;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.model.CityWeatherInfo;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.remote.OwmApi;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.remote.OwmApiService;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.remote.entities.CityWeatherDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryImpl implements Repository {
    public static final String APPID = "05812cf1b284ae27aed5160688e21bd9";
    public static final String UNITS = "metric";
    public static final String LANG = "sp";
    MutableLiveData<CityWeatherInfo> respuesta = null;

    private static RepositoryImpl instance;

    private final OwmApi api;

    private RepositoryImpl(Context context) {
        api = OwmApiService.getInstance().getOwmApi();
    }

    public static synchronized RepositoryImpl getInstance(Context context) {
        if (instance == null) {
            instance = new RepositoryImpl(context);
        }
        return instance;
    }

    @Override
    public MutableLiveData<CityWeatherInfo> getCityWeatherInfo(String city) {

        Call<CityWeatherDTO> call = api.getCityWeather(APPID,city,UNITS,LANG);

        call.enqueue(new Callback<CityWeatherDTO>() {
            @Override
            public void onResponse(Call<CityWeatherDTO> call, Response<CityWeatherDTO> response) {
                CityWeatherInfo info;
                respuesta = new MutableLiveData<>();

                if (response.isSuccessful() && response.body() != null) {
                    Double rainPerHour;

                    if(response.body().getRain() != null){
                        rainPerHour = response.body().getRain().get1h();
                    }else{
                        rainPerHour = 0.0d;
                    }
                    info = new CityWeatherInfo(
                            false
                            ,response.body().getName()
                            ,response.body().getWeather().get(0).getDescription()
                            ,response.body().getWeather().get(0).getIcon()
                            ,response.body().getWind().getDeg()
                            ,response.body().getClouds().getAll(),
                            response.body().getMain().getHumidity(),
                            response.body().getMain().getTemp(),
                            response.body().getMain().getTempMax(),
                            response.body().getMain().getTempMin(),
                            response.body().getWind().getSpeed(),
                            response.body().getSys().getSunrise(),
                            response.body().getSys().getSunset(),
                            rainPerHour);

                    respuesta.setValue(info);
                }else{
                    respuesta.setValue(new CityWeatherInfo(true));
                }


            }



            @Override
            public void onFailure(Call<CityWeatherDTO> call, Throwable t) {
                respuesta.setValue(new CityWeatherInfo(true));
            }

        });

        return respuesta;
    }
}
