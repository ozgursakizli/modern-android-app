package com.ozgursakizli.modernapp.viewmodel;

import com.ozgursakizli.modernapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountryListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public void refresh() {
        fetchCountries();
    }

    private void fetchCountries() {
        List<CountryModel> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            CountryModel countryModel = new CountryModel("Country " + (i + 1), "Capital " + (i + 1), "");
            list.add(countryModel);
        }

        countries.setValue(list);
        countryLoadError.setValue(false);
        loading.setValue(false);
    }

}
