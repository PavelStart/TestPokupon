package com.example.pavel.testpokupon.utilits;

import com.example.pavel.testpokupon.models.Config;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pavel on 12.05.2018.
 */

public class RetrofitSingleton {
    private static Retrofit retrofit;
    private static GitHubApi api;

    public static GitHubApi getApi(){
        if (api == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            api = retrofit.create(GitHubApi.class);
        }
        return api;
    }
}
