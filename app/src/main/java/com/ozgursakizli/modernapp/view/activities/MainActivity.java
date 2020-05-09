package com.ozgursakizli.modernapp.view.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ozgursakizli.modernapp.R;
import com.ozgursakizli.modernapp.view.adapters.LaunchListAdapter;
import com.ozgursakizli.modernapp.viewmodel.LaunchesListViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rv_launches_list)
    RecyclerView rvLaunches;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.pb_loading_view)
    ProgressBar pbLoadingView;

    private LaunchesListViewModel viewModel;
    private LaunchListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new LaunchListAdapter(new ArrayList<>());
        viewModel = ViewModelProviders.of(this).get(LaunchesListViewModel.class);
        viewModel.refresh();
        rvLaunches.setLayoutManager(new LinearLayoutManager(this));
        rvLaunches.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            refreshLayout.setRefreshing(false);
        });

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.launches.observe(this, countryModels -> {
            if (countryModels != null) {
                adapter.updateLaunches(countryModels);
                rvLaunches.setVisibility(View.VISIBLE);
            }
        });
        viewModel.launchesLoadError.observe(this, isError -> {
            if (isError != null) {
                tvError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null) {
                pbLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);

                if (isLoading) {
                    tvError.setVisibility(View.GONE);
                    rvLaunches.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_latest_event) {
            viewModel.fetchLatestLaunch();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
