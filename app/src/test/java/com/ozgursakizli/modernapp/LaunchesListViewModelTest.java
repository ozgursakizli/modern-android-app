package com.ozgursakizli.modernapp;

import com.ozgursakizli.modernapp.model.LaunchLinks;
import com.ozgursakizli.modernapp.model.LaunchesModel;
import com.ozgursakizli.modernapp.model.LaunchesService;
import com.ozgursakizli.modernapp.viewmodel.LaunchesListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class LaunchesListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    LaunchesService launchesService;

    @InjectMocks
    LaunchesListViewModel listViewModel = new LaunchesListViewModel();

    private Single<List<LaunchesModel>> testSingle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountriesSuccess() {
        LaunchesModel launchesModel = new LaunchesModel("missionName", "2020", new LaunchLinks("url"));
        ArrayList<LaunchesModel> countriesList = new ArrayList<>();
        countriesList.add(launchesModel);
        testSingle = Single.just(countriesList);
        Mockito.when(launchesService.getLaunches()).thenReturn(testSingle);
        listViewModel.refresh();
        Assert.assertEquals(1, listViewModel.launches.getValue().size());
        Assert.assertEquals(false, listViewModel.launchesLoadError.getValue());
        Assert.assertEquals(false, listViewModel.loading.getValue());
    }

    @Test
    public void getCountriesFail() {
        testSingle = Single.error(new Throwable());
        Mockito.when(launchesService.getLaunches()).thenReturn(testSingle);
        listViewModel.refresh();
        Assert.assertEquals(true, listViewModel.launchesLoadError.getValue());
        Assert.assertEquals(false, listViewModel.loading.getValue());
    }

    @Before
    public void setupRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run, true);
            }
        };

        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
    }

}
