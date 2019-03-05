package es.iessaldillo.nicol.nicolas_examenfinal_hilos.ui.fragments;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.Repository;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.RepositoryImpl;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.model.CityWeatherInfo;

public class WeatherFragmentViewModel extends ViewModel {

    private final Repository repository;
    private MutableLiveData<CityWeatherInfo> cityWeatherInfo = new MutableLiveData<>();
    private MutableLiveData<Boolean> hayError = new MutableLiveData<>();

    public WeatherFragmentViewModel(Context context) {
        repository = RepositoryImpl.getInstance(context);
    }

    public void requestCityWeatherInfo(String cityName) {
        MutableLiveData<CityWeatherInfo> cityWeatherInfo = repository.getCityWeatherInfo(cityName);
        if(cityWeatherInfo != null){
            if(cityWeatherInfo.getValue().isError()){
                hayError.setValue(true);
            }else{
                CityWeatherInfo result = cityWeatherInfo.getValue();
                this.cityWeatherInfo.setValue(result);
            }


        }
    }

    public MutableLiveData<Boolean> getHayError() {
        return hayError;
    }

    public void setHayError(MutableLiveData<Boolean> hayError) {
        this.hayError = hayError;
    }

    public MutableLiveData<CityWeatherInfo> getCityWeatherInfo() {
        return cityWeatherInfo;
    }

    public void setCityWeatherInfo(MutableLiveData<CityWeatherInfo> cityWeatherInfo) {
        this.cityWeatherInfo = cityWeatherInfo;
    }

    public String devolverHora (int sunrise_sunset){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        long time = (long)sunrise_sunset;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sunrise_sunset);
        try {
            return String.format("%s",format.parse(calendar.getTime().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
