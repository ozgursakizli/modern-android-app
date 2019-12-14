package com.ozgursakizli.modernapp.di;

import com.ozgursakizli.modernapp.model.CountriesService;
import com.ozgursakizli.modernapp.viewmodel.CountryListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountriesService service);

    void inject(CountryListViewModel viewModel);

}
