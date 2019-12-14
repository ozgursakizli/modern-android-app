package com.ozgursakizli.modernapp.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ozgursakizli.modernapp.R;
import com.ozgursakizli.modernapp.view.adapters.CountryListAdapter;
import com.ozgursakizli.modernapp.viewmodel.CountryListViewModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rv_countries_list)
    RecyclerView rvCountries;
    @BindView(R.id.tv_list_error)
    TextView tvError;
    @BindView(R.id.pb_loading_view)
    ProgressBar pbLoadingView;

    private CountryListViewModel viewModel;
    private CountryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new CountryListAdapter(new ArrayList<>());
        viewModel = ViewModelProviders.of(this).get(CountryListViewModel.class);
        viewModel.refresh();
        rvCountries.setLayoutManager(new LinearLayoutManager(this));
        rvCountries.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            refreshLayout.setRefreshing(false);
        });

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.countries.observe(this, countryModels -> {
            if (countryModels != null) {
                adapter.updateCountries(countryModels);
                rvCountries.setVisibility(View.VISIBLE);
            }
        });
        viewModel.countryLoadError.observe(this, isError -> {
            if (isError != null) {
                tvError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null) {
                pbLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);

                if (isLoading) {
                    tvError.setVisibility(View.GONE);
                    rvCountries.setVisibility(View.GONE);
                }
            }
        });
    }

}
