package com.fatihkirik.ramadanapp.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.fatihkirik.ramadanapp.Adapters.PrayerAdapter;
import com.fatihkirik.ramadanapp.Models.City;
import com.fatihkirik.ramadanapp.Models.Country;
import com.fatihkirik.ramadanapp.Models.Prayer;
import com.fatihkirik.ramadanapp.Models.Province;
import com.fatihkirik.ramadanapp.R;
import com.fatihkirik.ramadanapp.Services.PrayerAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Country> countryArrayList;
    ArrayList<City> cityArrayList;
    ArrayList<Province> provinceArrayList;
    ArrayList<Prayer> prayerArrayList;
    PrayerAdapter adapter;
    RecyclerView recyclerView;
    private String BASE_URL = "https://ezanvakti.herokuapp.com/";
    Retrofit retrofit;
    PrayerAPI prayerAPI;
    Country selectedCountry;
    City selectedCity;
    CompositeDisposable compositeDisposable;
    Province selectedProvince;
    Spinner spCountry , spCity , spProvince;
    ArrayAdapter<Country> countryArrayAdapter;
    ArrayAdapter<City> cityArrayAdapter;
    ArrayAdapter<Province> provinceArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryArrayList = new ArrayList<>();
        cityArrayList = new ArrayList<>();
        prayerArrayList = new ArrayList<>();

        compositeDisposable = new CompositeDisposable();

        spCountry = findViewById(R.id.spCountry);
        spCity = findViewById(R.id.spCity);
        spProvince = findViewById(R.id.spProvince);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        selectedCountry = new Country();
        selectedCity = new City();
        selectedProvince = new Province();

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        prayerAPI = retrofit.create(PrayerAPI.class);

        loadCountry();


        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCountry = countryArrayList.get(position);
                loadCity(selectedCountry.getCountryId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = cityArrayList.get(position);
                loadProvince(selectedCity.getCityId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProvince = provinceArrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






    }


    public void select(View view){

        if(selectedProvince!=null){

            loadPrayer(selectedProvince.getProvinceId());

            if(prayerArrayList!=null){
                adapter = new PrayerAdapter(prayerArrayList,selectedCountry,selectedCity,selectedProvince);
                recyclerView.setAdapter(adapter);
            }

        }
        else{

            Toast.makeText(this, "Please select a province", Toast.LENGTH_SHORT).show();

        }

    }


    private void loadCountry(){
        //Schedulers.io() : Input Output işlemlerini bloke etmeden işlemleri yapmamızı sağlar

        compositeDisposable.add(prayerAPI.getCountry()
            .subscribeOn(Schedulers.io())//Hangi thread de subscribe olunacağını belirtiyoruz
            .observeOn(AndroidSchedulers.mainThread())//Hangi thread de gözlemleme yapacağımızı belirtiyoruz
            .subscribe(this::handleCountryResponse));

        /*
        Call<List<Country>> call = prayerAPI.getCountry();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful()){
                    List<Country> countries = response.body();
                    countryArrayList = new ArrayList<>(countries);

                    countryArrayAdapter = new ArrayAdapter<Country>(MainActivity.this, R.layout.spinner_list_view,countryArrayList);
                    countryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spCountry.setAdapter(countryArrayAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */

    }

    private void handleCountryResponse(List<Country> countryList){
        countryArrayList = new ArrayList<>(countryList);

        countryArrayAdapter = new ArrayAdapter<Country>(MainActivity.this, R.layout.spinner_list_view,countryArrayList);
        countryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountry.setAdapter(countryArrayAdapter);
    }

    private void loadCity(int countryId){

        //Schedulers.io() : Input Output işlemlerini bloke etmeden işlemleri yapmamızı sağlar

        //Schedulers.io() : Input Output işlemlerini bloke etmeden işlemleri yapmamızı sağlar

        compositeDisposable.add(prayerAPI.getCity(countryId)
                .subscribeOn(Schedulers.io())//Hangi thread de subscribe olunacağını belirtiyoruz
                .observeOn(AndroidSchedulers.mainThread())//Hangi thread de gözlemleme yapacağımızı belirtiyoruz
                .subscribe(this::handleCityResponse));



        /*
        Call<List<City>> call = prayerAPI.getCity(countryId);

        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                if(response.isSuccessful()){

                    List<City> cities = response.body();
                    cityArrayList = new ArrayList<>(cities);

                    cityArrayAdapter = new ArrayAdapter<City>(MainActivity.this, R.layout.spinner_list_view,cityArrayList);
                    cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spCity.setAdapter(cityArrayAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */

    }

    private void handleCityResponse(List<City> cityList){
        cityArrayList = new ArrayList<>(cityList);

        cityArrayAdapter = new ArrayAdapter<City>(MainActivity.this, R.layout.spinner_list_view,cityArrayList);
        cityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(cityArrayAdapter);
    }


    private void loadProvince(int cityId){

        compositeDisposable.add(prayerAPI.getProvince(cityId)
                .subscribeOn(Schedulers.io())//Hangi thread de subscribe olunacağını belirtiyoruz
                .observeOn(AndroidSchedulers.mainThread())//Hangi thread de gözlemleme yapacağımızı belirtiyoruz
                .subscribe(this::handleProvinceResponse));

        /*
        Call<List<Province>> call = prayerAPI.getProvince(cityId);

        call.enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {

                if(response.isSuccessful()){
                    List<Province> provinces = response.body();
                    provinceArrayList = new ArrayList<>(provinces);

                    provinceArrayAdapter = new ArrayAdapter<Province>(MainActivity.this, R.layout.spinner_list_view,provinceArrayList);
                    provinceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spProvince.setAdapter(provinceArrayAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {
                t.printStackTrace();
            }
        });
         */

    }

    private void handleProvinceResponse(List<Province> provinceList){

        provinceArrayList = new ArrayList<>(provinceList);

        provinceArrayAdapter = new ArrayAdapter<Province>(MainActivity.this, R.layout.spinner_list_view,provinceArrayList);
        provinceArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProvince.setAdapter(provinceArrayAdapter);
    }


    private void loadPrayer(int provinceId){

        compositeDisposable.add(prayerAPI.getPrayer(provinceId)
                .subscribeOn(Schedulers.io())//Hangi thread de subscribe olunacağını belirtiyoruz
                .observeOn(AndroidSchedulers.mainThread())//Hangi thread de gözlemleme yapacağımızı belirtiyoruz
                .subscribe(this::handlePrayerResponse));

        /*
        Call<List<Prayer>> call = prayerAPI.getPrayer(provinceId);

        call.enqueue(new Callback<List<Prayer>>() {
            @Override
            public void onResponse(Call<List<Prayer>> call, Response<List<Prayer>> response) {
                if(response.isSuccessful()){

                    List<Prayer> prayers = response.body();
                    prayerArrayList = new ArrayList<>(prayers);

                    //adapter = new PrayerAdapter(prayerArrayList,selectedCountry,selectedCity,selectedProvince);
                    //recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Prayer>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */
    }

    private void handlePrayerResponse(List<Prayer> prayerList){

        prayerArrayList = new ArrayList<>(prayerList);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();

    }
}