package es.iessaldillo.nicol.nicolas_examenfinal_hilos.ui.fragments;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class WeatherFragmentViewModelFactory implements ViewModelProvider.Factory {
    private final Context context;

    public WeatherFragmentViewModelFactory(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WeatherFragmentViewModel(context);
    }
}
