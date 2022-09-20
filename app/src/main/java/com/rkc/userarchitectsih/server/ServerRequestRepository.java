package com.rkc.userarchitectsih.server;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServerRequestRepository {
    private static ServerRequestRepository instance;

    private ServerServices serverServices;

    public static ServerRequestRepository getInstance() {
        if (instance == null) {
            instance = new ServerRequestRepository();
        }
        return instance;
    }

    public ServerRequestRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        client.readTimeoutMillis();
        client.writeTimeoutMillis();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dvpysociety.herokuapp.com/")
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverServices = retrofit.create(ServerServices.class);
    }

    public ServerServices getServerService() {
        return serverServices;
    }
}
