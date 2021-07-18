package com.fatihkirik.ramadanapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fatihkirik.ramadanapp.Models.City;
import com.fatihkirik.ramadanapp.Models.Country;
import com.fatihkirik.ramadanapp.Models.Prayer;
import com.fatihkirik.ramadanapp.Models.Province;
import com.fatihkirik.ramadanapp.R;

import java.util.ArrayList;

public class PrayerAdapter extends RecyclerView.Adapter<PrayerAdapter.RowHolder> {


    ArrayList<Prayer> prayerArrayList;
    Country country;
    City city;
    Province province;

    public PrayerAdapter(ArrayList<Prayer> prayerArrayList, Country country, City city, Province province) {
        this.prayerArrayList = prayerArrayList;
        this.country = country;
        this.city = city;
        this.province = province;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.custom_time_view,parent,false);

        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

        Prayer prayer = prayerArrayList.get(position);

        holder.tvGun.setText(position+1+". Gün");
        holder.tvCountryName.setText(country.getCountryName());
        holder.tvCityName.setText(city.getCityName());
        holder.tvProvinceName.setText(province.getProvinceName());
        holder.tvSahurTime.setText(prayer.getSahur());
        holder.tvIftarTime.setText(prayer.getIftar());

    }

    @Override
    public int getItemCount() {
        return prayerArrayList.size();
    }

    class RowHolder extends RecyclerView.ViewHolder{
        TextView tvGun, tvCountryName , tvCityName , tvProvinceName , tvIftarTime , tvSahurTime;
        CardView cardView;
        public RowHolder(@NonNull View itemView) {
            super(itemView);

            tvGun = itemView.findViewById(R.id.tvGun);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            tvCityName = itemView.findViewById(R.id.tvCityName);
            tvProvinceName = itemView.findViewById(R.id.tvProvinceName);
            tvIftarTime = itemView.findViewById(R.id.tvIftarTime);
            tvSahurTime = itemView.findViewById(R.id.tvSahurTime);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

}
