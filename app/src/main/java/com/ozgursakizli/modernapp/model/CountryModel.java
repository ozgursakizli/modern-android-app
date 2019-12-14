package com.ozgursakizli.modernapp.model;

public class CountryModel {

    private String countryName;
    private String capital;
    private String flag;

    public CountryModel(String countryName, String capital, String flag) {
        this.countryName = countryName;
        this.capital = capital;
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

}
