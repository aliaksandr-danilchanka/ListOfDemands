package myproject.questlistofdemands.api;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aliaksandr on 9/19/2017.
 */

public class RestHelper {

    private static DemandsInterface sDemandsInterface;

    public static DemandsInterface getInterface() {
        return new Retrofit.Builder()
                .baseUrl("https://server.qest.cz:44302/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .client(getOkHttpClient())
                .build().create(DemandsInterface.class);
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

    }
}
