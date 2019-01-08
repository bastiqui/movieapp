package com.example.bastiqui.moviesapp;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviedbModule {
    static MoviedbAPI moviedbAPI;

    public static MoviedbAPI getAPI(){
        if(moviedbAPI == null){
            final OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .addInterceptor(new ApiKeyInterceptor())
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15,TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            moviedbAPI = retrofit.create(MoviedbAPI.class);
        }
        return moviedbAPI;
    }
}

class ApiKeyInterceptor implements Interceptor {
    @NonNull
    @Override
    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "a9e7156a400eacb271ae76b3a4d58140")
                .addQueryParameter("language", "es")
                .addQueryParameter("append_to_response", "videos")
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

class LoggingInterceptor implements Interceptor {
    @Override public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.e("INTERCEPTOR", String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        okhttp3.Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.e("INTERCEPTOR---", String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}