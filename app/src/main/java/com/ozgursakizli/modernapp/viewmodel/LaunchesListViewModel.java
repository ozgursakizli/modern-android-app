package com.ozgursakizli.modernapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ozgursakizli.modernapp.di.DaggerApiComponent;
import com.ozgursakizli.modernapp.model.LaunchesModel;
import com.ozgursakizli.modernapp.service.LaunchesService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LaunchesListViewModel extends ViewModel {

    public MutableLiveData<List<LaunchesModel>> launches = new MutableLiveData<>();
    public MutableLiveData<LaunchesModel> latestLaunch = new MutableLiveData<>();
    public MutableLiveData<Boolean> launchesLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    LaunchesService launchesService;

    public LaunchesListViewModel() {
        super();
        DaggerApiComponent.create().inject(this);
    }

    public void refresh() {
        fetchLaunches();
    }

    private void fetchLaunches() {
        loading.setValue(true);
        disposable.add(launchesService.getLaunches()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<LaunchesModel>>() {
                    @Override
                    public void onSuccess(List<LaunchesModel> launchesModels) {
                        launches.setValue(launchesModels);
                        launchesLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        launchesLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );
    }

    public void fetchLatestLaunch() {
        disposable.add(launchesService.getLatestLaunch()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LaunchesModel>() {
                    @Override
                    public void onSuccess(LaunchesModel launchesModel) {
                        latestLaunch.setValue(launchesModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
