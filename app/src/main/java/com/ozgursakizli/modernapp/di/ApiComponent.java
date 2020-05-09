package com.ozgursakizli.modernapp.di;

import com.ozgursakizli.modernapp.service.LaunchesService;
import com.ozgursakizli.modernapp.viewmodel.LaunchesListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(LaunchesService service);

    void inject(LaunchesListViewModel viewModel);

}
