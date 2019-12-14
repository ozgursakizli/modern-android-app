package com.ozgursakizli.modernapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ozgursakizli.modernapp.R;
import com.ozgursakizli.modernapp.model.CountryModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private List<CountryModel> countries;

    public CountryListAdapter(List<CountryModel> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void updateCountries(List<CountryModel> newCountries) {
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_flag)
        ImageView countryFlag;
        @BindView(R.id.tv_name)
        TextView tvCountryName;
        @BindView(R.id.tv_capital)
        TextView tvCountryCapital;

        CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(CountryModel country) {
            tvCountryName.setText(country.getCountryName());
            tvCountryCapital.setText(country.getCapital());
        }

    }

}
