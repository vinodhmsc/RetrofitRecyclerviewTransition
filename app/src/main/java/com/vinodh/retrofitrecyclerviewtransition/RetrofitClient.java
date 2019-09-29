package com.vinodh.retrofitrecyclerviewtransition;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VINODH KUMAR
 */
public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getClient(String base_url){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
