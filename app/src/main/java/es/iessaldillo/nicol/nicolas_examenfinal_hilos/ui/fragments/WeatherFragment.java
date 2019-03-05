package es.iessaldillo.nicol.nicolas_examenfinal_hilos.ui.fragments;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import es.iessaldillo.nicol.nicolas_examenfinal_hilos.R;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.model.CityWeatherInfo;
import es.iessaldillo.nicol.nicolas_examenfinal_hilos.services.NotificationService;

public class WeatherFragment extends Fragment {

    private WeatherFragmentViewModel vm;
    private TextView lblCity,lblDescriptionResult,lblTempResult,lblHumidityResult,lblWindResult,lblCloudiness,lblDuskDawnResult,lblRainResult;
    private ImageView imgWeatherIcon;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weather_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(this,new WeatherFragmentViewModelFactory(requireContext())).get(WeatherFragmentViewModel.class);
        setupViews();
        vm.getCityWeatherInfo().observe(requireActivity(),info -> showResult(info));
        vm.getHayError().observe(requireActivity(),hayerror -> showSnackBar(hayerror));
    }

    private void showSnackBar(boolean error) {
        if(error){
            Snackbar.make(lblCity,"Ha ocurrido un error con la petición",Snackbar.LENGTH_SHORT).show();
        }
    }

    private void setupViews() {
        EditText txtCity = ViewCompat.requireViewById(getView(), R.id.txtName);
        lblCity = ViewCompat.requireViewById(getView(), R.id.lblCity);
        lblDescriptionResult = ViewCompat.requireViewById(getView(), R.id.lblDescriptionResult);
        lblTempResult = ViewCompat.requireViewById(getView(), R.id.lblTempResult);
        imgWeatherIcon = ViewCompat.requireViewById(getView(),R.id.imgWeatherIcon);
        lblHumidityResult = ViewCompat.requireViewById(getView(), R.id.lblHumidityResult);
        lblWindResult = ViewCompat.requireViewById(getView(), R.id.lblWindResult);
        lblCloudiness = ViewCompat.requireViewById(getView(), R.id.lblCloudinessResult);
        lblDuskDawnResult = ViewCompat.requireViewById(getView(), R.id.lblDuskDawnResult);
        lblRainResult = ViewCompat.requireViewById(getView(),R.id.lblRainResult);

        FloatingActionButton fab = ViewCompat.requireViewById(getView(),R.id.fabSearch);

        fab.setOnClickListener(v -> vm.requestCityWeatherInfo(txtCity.getText().toString()));

    }

    private void showResult(CityWeatherInfo info) {
        lblCity.setText(info.getCityName());
        lblDescriptionResult.setText(info.getDescription());
        lblTempResult.setText(String.format("min %.2f ºC\nmax %.2f ºC\nmedia %.2f ºC",info.getMain_temp_min(),info.getMain_temp_max(),info.getMain_temp()));
        lblHumidityResult.setText(String.format("%d %%",info.getHumidity()));
        lblWindResult.setText(String.format("velocidad %f mps\ndireccion %d",info.getWind_speed(),info.getWind_degrees()));
        lblCloudiness.setText(String.format("%d %%",info.getClouds()));
        Picasso.with(getContext()).load(String.format("http://openweathermap.org/img/w/%s.png",info.getIcon())).into(imgWeatherIcon);
        lblRainResult.setText(String.format("%f",info.getRain()));
        lblDuskDawnResult.setText(String.format("Amanecer: %s Atardecer%s",vm.devolverHora(info.getSunrise()),vm.devolverHora(info.getSunset())));
        NotificationService.start(getContext(),info.getCityName(),info.getDescription());
    }

}
