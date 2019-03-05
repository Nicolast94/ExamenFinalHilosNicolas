package es.iessaldillo.nicol.nicolas_examenfinal_hilos.data;

import androidx.lifecycle.MutableLiveData;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.model.CityWeatherInfo;

public interface Repository {
    MutableLiveData<CityWeatherInfo> getCityWeatherInfo(String city);
}
