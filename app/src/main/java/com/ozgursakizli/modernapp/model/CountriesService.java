package com.ozgursakizli.modernapp.model;

import com.ozgursakizli.modernapp.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountriesService {

    private static volatile CountriesService instance;

    @Inject
    CountriesApi api;

    private CountriesService() {
        DaggerApiComponent.create().inject(this);
    }

    public static CountriesService getInstance() {
        if (instance == null) {
            synchronized (CountriesService.class) {
                if (instance == null) {
                    instance = new CountriesService();
                }
            }
        }

        return instance;
    }

    public Single<List<CountryModel>> getCountries() {
        return api.getCountries();
    }

}
