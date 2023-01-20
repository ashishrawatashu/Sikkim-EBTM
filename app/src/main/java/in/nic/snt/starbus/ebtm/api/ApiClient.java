package in.nic.snt.starbus.ebtm.api;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static final String BASE_URL = "https://utcdemo.uk.gov.in/utcaudit/utcETM/wsETM.asmx/";

    private ApiClient() {

    }

    private static ApiInterface mApiInterface;

    //service before login
    public static ApiInterface getApiInterface() {
        return (mApiInterface == null) ? setApiInterface() : mApiInterface;
    }

    private static ApiInterface setApiInterface() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build();
        Retrofit.Builder mBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());
            mApiInterface = mBuilder.build().create(ApiInterface.class);

        return mApiInterface;
    }

}
