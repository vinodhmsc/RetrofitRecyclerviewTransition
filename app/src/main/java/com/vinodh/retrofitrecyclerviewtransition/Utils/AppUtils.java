package com.vinodh.retrofitrecyclerviewtransition.Utils;

import com.vinodh.retrofitrecyclerviewtransition.Interface.Api;
import com.vinodh.retrofitrecyclerviewtransition.RetrofitClient;

/**
 * Created by VINODH KUMAR
 */
public class AppUtils {

    public static Api getMarvelHeros(){
        return RetrofitClient.getClient(AppConstants.BASE_URL).create(Api.class);
    }
}
