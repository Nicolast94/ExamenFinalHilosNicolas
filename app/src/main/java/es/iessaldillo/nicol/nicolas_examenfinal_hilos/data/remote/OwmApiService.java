package es.iessaldillo.nicol.nicolas_examenfinal_hilos.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OwmApiService {
    private static OwmApiService INSTANCE;
    private final OwmApi owmApi;

    public static OwmApiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OwmApiService(buildInstance());
        }
        return INSTANCE;
    }

    private static OwmApi buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(OwmApi.class);
    }

    private OwmApiService(OwmApi owmApi) {
        this.owmApi = owmApi;
    }

    public OwmApi getOwmApi() {
        return owmApi;
    }
}
