package com.ozgursakizli.modernapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.ozgursakizli.modernapp.model.LaunchLinks;
import com.ozgursakizli.modernapp.model.LaunchesModel;
import com.ozgursakizli.modernapp.service.LaunchesService;
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

    private Single<List<LaunchesModel>> testSingleList;
    private Single<LaunchesModel> testSingle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLaunchesSuccess() {
        LaunchesModel launchesModel = new LaunchesModel("missionName", "2020", new LaunchLinks("url"));
        ArrayList<LaunchesModel> launchesModels = new ArrayList<>();
        launchesModels.add(launchesModel);
        testSingleList = Single.just(launchesModels);
        Mockito.when(launchesService.getLaunches()).thenReturn(testSingleList);
        listViewModel.refresh();
        Assert.assertEquals(1, listViewModel.launches.getValue().size());
        Assert.assertEquals(false, listViewModel.launchesLoadError.getValue());
        Assert.assertEquals(false, listViewModel.loading.getValue());
    }

    @Test
    public void getLaunchesFail() {
        testSingleList = Single.error(new Throwable());
        Mockito.when(launchesService.getLaunches()).thenReturn(testSingleList);
        listViewModel.refresh();
        Assert.assertEquals(true, listViewModel.launchesLoadError.getValue());
        Assert.assertEquals(false, listViewModel.loading.getValue());
    }

    @Test
    public void getLatestLaunchSuccess() {
        LaunchesModel launchesModel = new LaunchesModel("latestMission", "2020", new LaunchLinks("url"));
        testSingle = Single.just(launchesModel);
        Mockito.when(launchesService.getLatestLaunch()).thenReturn(testSingle);
        listViewModel.fetchLatestLaunch();
        Assert.assertNotNull(listViewModel.latestLaunch.getValue());
    }

    @Test
    public void getLatestLaunchFail() {
        testSingle = Single.error(new Throwable());
        Mockito.when(launchesService.getLatestLaunch()).thenReturn(testSingle);
        listViewModel.fetchLatestLaunch();
        Assert.assertNull(listViewModel.latestLaunch.getValue());
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
