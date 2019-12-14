package com.ozgursakizli.modernapp.model;

import com.google.gson.annotations.SerializedName;

public class LaunchesModel {

    @SerializedName("mission_name")
    private String missionName;
    @SerializedName("launch_year")
    private String launchYear;
    @SerializedName("links")
    private LaunchLinks launchLinks;

    public LaunchesModel(String missionName, String launchYear, LaunchLinks launchLinks) {
        this.missionName = missionName;
        this.launchYear = launchYear;
        this.launchLinks = launchLinks;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getLaunchYear() {
        return launchYear;
    }

    public LaunchLinks getLaunchLinks() {
        return launchLinks;
    }

}
