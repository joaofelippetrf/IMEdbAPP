package com.example.imedb.movieapp;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient {

    private static final String BASE_URL = "http://192.168.93.46:8081/";  // Use o IP correto

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            // Configurando o interceptor para log
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);  // Exibe todos os detalhes das requisições

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)  // Adiciona o interceptor para logs
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)  // Configura o cliente com o interceptor
                    .build();
        }
        return retrofit;
    }
}