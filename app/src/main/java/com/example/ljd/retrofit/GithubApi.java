package com.example.ljd.retrofit;

import com.example.ljd.retrofit.pojo.Contributor;
import com.example.ljd.retrofit.pojo.RetrofitBean;
import com.example.ljd.retrofit.pojo.User;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 　在retrofit中通过一个Java接口作为http请求的api接口。
 */
public interface GithubApi {

    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleGetCall(@Path("owner") String owner, @Path("repo") String repo);

    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributorsByAddConverterGetCall(@Path("owner") String owner, @Path("repo") String repo);


    //可以使用@Headers注解给函数设置静态的header
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: RetrofitBean-Sample-App",
            "name:ljd"
    })
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributorsAndAddHeader(@Path("owner") String owner, @Path("repo") String repo);

    //get方式请求静态url地址
    @GET("search/repositories")
    Call<RetrofitBean> queryRetrofitByGetCall(@Query("q") String owner,
                                              @Query("since") String time,
                                              @Query("page") int page,
                                              @Query("per_page") int per_Page);

    //get方式请求静态url地址
    @GET("search/repositories")
    Call<RetrofitBean> queryRetrofitByGetCallMap(@QueryMap Map<String, String> map);



    //少数参数
    @FormUrlEncoded
    @POST("users/stven0king/repos")
    Call<List<User>> listRepos(@Field("time") long time);

    //参数较多
    @FormUrlEncoded
    @POST("users/stven0king/repos")
    Call<List<User>> listRepos(@FieldMap Map<String, String> params);

    /**
     * Cmethod  请求方法，不区分大小写
     * path    路径
     * hasBody 是否有请求体
     */
    @HTTP(method = "get", path = "blog/{id}", hasBody = false)
    Call<ResponseBody> getBlog(@Path("id") int id);


    @GET("group/{id}/users")
    List<User> groupList(@Path("id") int groupId, @Query("sort") String sort);
    //组合请求串:group/{id}/users?sort="desc";


    @GET("group/{id}/users")
    List<User> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
    //

}
