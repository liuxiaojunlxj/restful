package com.example.ljd.retrofit;


import android.text.TextUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ljd on 3/25/16.
 */
public class GithubService {

    //在这里baseUrl是在创建retrofit实力的时候定义的，我们也可以在API接口中定义完整的url。在这里建议在创建baseUrl中以”/”结尾，在API中不以”/”开头和结尾。
    public static String BASE_URL = "https://api.github.com/";

    private GithubService() {
    }

    public static <T> T createRetrofitService(String baseurl, final Class<T> service) {

        if (!TextUtils.isEmpty(baseurl)) {
            BASE_URL = baseurl;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(service);
    }

}
