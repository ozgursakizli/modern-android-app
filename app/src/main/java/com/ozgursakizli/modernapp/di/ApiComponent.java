package com.ozgursakizli.modernapp.di;

import com.ozgursakizli.modernapp.model.CountriesService;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountriesService service);

}
