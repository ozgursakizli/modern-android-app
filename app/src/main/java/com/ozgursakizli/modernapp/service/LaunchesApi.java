package com.ozgursakizli.modernapp.service;

import com.ozgursakizli.modernapp.model.LaunchesModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface LaunchesApi {

    @GET("launches")
    Single<List<LaunchesModel>> getLaunches();

    @GET("launches/latest")
    Single<LaunchesModel> getLatestLaunch();

}
