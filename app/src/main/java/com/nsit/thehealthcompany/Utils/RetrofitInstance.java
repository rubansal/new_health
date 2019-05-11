package com.nsit.thehealthcompany.Utils;

import androidx.annotation.NonNull;
import com.nsit.thehealthcompany.Retrofit.ApiInterface;
import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;

    private static OkHttpClient getOkHttpClient(){
        return new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic("alnourish.official@gmail.com", "alnourish_thc"));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();
    }

    public static ApiInterface getApiInterface(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.thehealthycompany.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }

}
