package com.pillll.pillll.remoteDataSource;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class used to build and provide an instance of Retrofit object
 *
 * @author Romain Pardoux
 * @version 1.0
 */
public class NetworkService {

    private static NetworkService instance;
    public static final String ENDPOINT = "https://www.pillll.com/api/";
    private Retrofit retrofit;

    public NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance(){
        if (instance == null){
            instance = new NetworkService();
        }
        return instance;
    }

    public PillllWebService getPillllApi(){
        return retrofit.create(PillllWebService.class);
    }
}
