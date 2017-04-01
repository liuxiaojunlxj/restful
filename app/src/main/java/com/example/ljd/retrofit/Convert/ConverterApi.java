package com.example.ljd.retrofit.Convert;

import android.graphics.Bitmap;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Copyright (C) 2014 Guangzhou QTONE Technologies Ltd.
 * <p>
 * 本代码版权归广州全通教育股份有限公司所有，且受到相关的法律保护。没有经过版权所有者的书面同意，
 * 任何其他个人或组织均不得以任何形式将本文件或本文件的部分代码用于其他商业用途。
 *
 * @author：xjliu
 * @Emai：782786731@qq.com
 * @date： 17/3/30 11:04
 * @version：V1.0
 */
public interface ConverterApi {
    //path 是完整的 Url：
    @GET("https://ss0.baidu.com/73F1bjeh1BF3odCf/it/u=2537069448,2929136489&fm=85&s=85B8ED321DD844CA4EED10DE000070B1")
    Call<Bitmap> getString();
}
