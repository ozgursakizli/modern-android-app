package com.ozgursakizli.modernapp.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ozgursakizli.modernapp.R;
import com.ozgursakizli.modernapp.model.LaunchesModel;
import com.ozgursakizli.modernapp.utility.ImageUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LaunchListAdapter extends RecyclerView.Adapter<LaunchListAdapter.LaunchViewHolder> {

    private List<LaunchesModel> launches;

    public LaunchListAdapter(List<LaunchesModel> launches) {
        this.launches = launches;
    }

    @NonNull
    @Override
    public LaunchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_launch, parent, false);
        return new LaunchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaunchViewHolder holder, int position) {
        holder.bind(launches.get(position));
    }

    @Override
    public int getItemCount() {
        return launches.size();
    }

    public void updateLaunches(List<LaunchesModel> newLaunches) {
        launches.clear();
        launches.addAll(newLaunches);
        notifyDataSetChanged();
    }

    static class LaunchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_launch_patch)
        ImageView launchPatch;
        @BindView(R.id.tv_name)
        TextView tvMissionName;
        @BindView(R.id.tv_year)
        TextView tvMissionYear;

        LaunchViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(LaunchesModel country) {
            tvMissionName.setText(country.getMissionName());
            tvMissionYear.setText(country.getLaunchYear());
            ImageUtils.loadImage(launchPatch, country.getLaunchLinks().getMissionPatchSmall(), ImageUtils.getProgressDrawable(launchPatch.getContext()));
        }

    }

}
