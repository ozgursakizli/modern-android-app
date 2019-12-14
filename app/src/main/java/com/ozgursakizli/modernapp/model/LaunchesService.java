package com.ozgursakizli.modernapp.model;

import com.ozgursakizli.modernapp.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class LaunchesService {

    private static volatile LaunchesService instance;

    @Inject
    LaunchesApi api;

    private LaunchesService() {
        DaggerApiComponent.create().inject(this);
    }

    public static LaunchesService getInstance() {
        if (instance == null) {
            synchronized (LaunchesService.class) {
                if (instance == null) {
                    instance = new LaunchesService();
                }
            }
        }

        return instance;
    }

    public Single<List<LaunchesModel>> getLaunches() {
        return api.getLaunches();
    }

}
