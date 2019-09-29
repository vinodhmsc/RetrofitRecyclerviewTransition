package com.vinodh.retrofitrecyclerviewtransition.Interface;

import com.vinodh.retrofitrecyclerviewtransition.Model.MarvelHeros;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by VINODH KUMAR
 */
public interface Api {

    @GET("marvel")
    Call<List<MarvelHeros>> getHeros();
}
