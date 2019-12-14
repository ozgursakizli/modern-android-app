package com.ozgursakizli.modernapp.model;

import com.google.gson.annotations.SerializedName;

public class LaunchLinks {

    @SerializedName("mission_patch_small")
    private String missionPatchSmall;

    public LaunchLinks(String missionPatchSmall) {
        this.missionPatchSmall = missionPatchSmall;
    }

    public String getMissionPatchSmall() {
        return missionPatchSmall;
    }

}
