package com.fatihkirik.ramadanapp.Services;

import com.fatihkirik.ramadanapp.Models.City;
import com.fatihkirik.ramadanapp.Models.Country;
import com.fatihkirik.ramadanapp.Models.Prayer;
import com.fatihkirik.ramadanapp.Models.Province;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PrayerAPI {

    @GET("ulkeler")
    Observable<List<Country>> getCountry();

    @GET("sehirler/{countryId}")
    Observable<List<City>> getCity(@Path("countryId") int countryId);

    @GET("ilceler/{cityId}")
    Observable<List<Province>> getProvince(@Path("cityId") int cityId);

    @GET("vakitler/{provinceId}")
    Observable<List<Prayer>> getPrayer(@Path("provinceId") int provinceId);

}
