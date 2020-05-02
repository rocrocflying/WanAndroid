package network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static network.Request.BASE_URL;
import static network.Request.GANKIO_URL;

public class RetrofitManager {

    private static RetrofitManager retrofitManager;
    private Retrofit retrofit;
    private Request request;
    private Retrofit meizhiRetrofit;
    private Request meizhiRequest;
    public static RetrofitManager getInstance() {

        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }

        return retrofitManager;
    }

    public void init() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public void initMeizhi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        meizhiRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(GANKIO_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }



    public Request getRequest() {
        if (request == null) {
            request = retrofit.create(Request.class);
        }
        return request;
    }

    public Request getMeizhiRequest() {
        if (meizhiRequest == null) {
            meizhiRequest = meizhiRetrofit.create(Request.class);
        }
        return meizhiRequest;
    }
}
