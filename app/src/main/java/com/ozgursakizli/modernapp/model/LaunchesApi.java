package com.ozgursakizli.modernapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface LaunchesApi {

    @GET("launches")
    Single<List<LaunchesModel>> getLaunches();

    @GET("launches/latest")
    Single<LaunchesModel> getLatestLaunch();

}
