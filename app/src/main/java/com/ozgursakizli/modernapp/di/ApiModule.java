package com.ozgursakizli.modernapp.di;

import com.ozgursakizli.modernapp.service.LaunchesApi;
import com.ozgursakizli.modernapp.service.LaunchesService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class ApiModule {

    private static final String BASE_URL = "https://api.spacexdata.com/v3/";

    @Provides
    LaunchesApi provideLaunchesApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(LaunchesApi.class);
    }

    @Provides
    LaunchesService provideLaunchesService() {
        return LaunchesService.getInstance();
    }

}
