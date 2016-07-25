package com.ciandt.dojoandroid.marvelsapp.services;

import com.ciandt.dojoandroid.marvelsapp.interfaces.MarvelServiceBase;
import com.ciandt.dojoandroid.marvelsapp.utils.commons.Common;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Vitor on 24/07/2016.
 */
public class MarvelService  {

    public static MarvelServiceBase getService(String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(Common.getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MarvelServiceBase.class);
    }
}
