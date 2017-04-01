package com.example.ljd.retrofit.Convert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.ljd.retrofit.R;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConverterActivity extends AppCompatActivity {
    //用注解 完成实例化
    public ImageView iv;
    private ConverterApi converterApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        iv= (ImageView) findViewById(R.id.iv);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com/")
                .addConverterFactory(BitMapCoverterFactory.create())
                .build();
        converterApi = retrofit.create(ConverterApi.class);
        converterApi.getString().enqueue(new Callback<Bitmap>() {
            @Override
            public void onResponse(Call<Bitmap> call, Response<Bitmap> response) {
                iv.setImageBitmap(response.body());
            }

            @Override
            public void onFailure(Call<Bitmap> call, Throwable t) {

            }
        });

    }

    public static class UserResponseConverter<T> implements Converter<ResponseBody, T> {
        private Type type;

        public UserResponseConverter(Type type) {
            this.type = type;
        }

        @Override
        public T convert(ResponseBody responseBody) throws IOException {
            byte[] bytes = responseBody.bytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return (T) bitmap;
        }
    }


    static class BitMapCoverterFactory extends Converter.Factory {
        @Override
        //方法为网络调用后 使用
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            //如果出类型为Bitmap 那么就处理用UserResponseConverter 类处理
            // 我们稍后再看这个类
            //如果不为这个处理类那么返回空交给另一个转化器处理
            if (type == Bitmap.class)
                return new UserResponseConverter(type);
            return null;
        }

        private static BitMapCoverterFactory bitMapCoverterFactory;

        static BitMapCoverterFactory create() {

            if (bitMapCoverterFactory == null) {
                bitMapCoverterFactory = new BitMapCoverterFactory();
            }
            return bitMapCoverterFactory;
        }

        private BitMapCoverterFactory() {

        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }
    }

}
