package com.example.alex.test_italki;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import org.reactivestreams.Publisher;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alex on 18-1-4.
 */

public class ItalkiApi {

    private ItalkiService service;
    private Gson gson;


    private static SSLContext sslContext;

    private static final String TAG = ItalkiApi.class.getSimpleName();
    public static final String PARAM_LOGIN_USERNAME = "LOGIN_PA_USERNAME";
    public static final String PARAM_LOGIN_PASSWORD = "LOGIN_PA_PASSWORD";

    static {

        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        }};

        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
        } catch (Exception e) {
            Log.e(TAG, "Error with SSL shit: " + e.getMessage(), e);
        }

    }


    public ItalkiApi() {
        initItalkiService(getEndPoint());
    }


    private void initItalkiService(String endpoint) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        this.service = retrofit.create(ItalkiService.class);
    }


    @Nullable
    private String getEndPoint() {
        // we need the android context for the cookie store and SSL
        return "https://xwww.italki.com";
    }


    @NonNull
    private OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .protocols(getProtocols());

        return builder.build();
    }

    @NonNull
    private ArrayList<Protocol> getProtocols() {
        ArrayList<Protocol> protocolList = new ArrayList<>();
        protocolList.add(Protocol.HTTP_1_1);
        return protocolList;
    }

    public Observable<User> loginRxJava(String userName, String password) {
        return service.loginRxJava(userName, password).map(ItalkiResponse::getData);
    }


    public Flowable<User> loginRxJava2(String userName, String password) {
        return service.loginRxJava2(userName, password).map(ItalkiResponse::getData);

    }

    public Flowable loginRxJava2GetHeaders(String userName, String password) {
        return service.loginRxJava2GetHeaders(userName, password).flatMap(new Function<Response<ItalkiResponse<User>>, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Response<ItalkiResponse<User>> response) throws Exception {

                Set<String> names = response.headers().names();
                User user = response.body().getData();
                Log.d(TAG, "onResponse: ------------user:" + user.toString());
                Log.d(TAG, "onResponse: ----------header names:" + names);
                return null;
            }
        });
    }

    public void loginRetrofit(String userName, String password) {

        Call<ItalkiResponse<User>> call = service.loginRetrofit(userName, password);
        call.enqueue(new Callback<ItalkiResponse<User>>() {
            @Override
            public void onResponse(Call<ItalkiResponse<User>> call, Response<ItalkiResponse<User>> response) {
                Set<String> names = response.headers().names();
                User user = response.body().getData();
                Log.d(TAG, "onResponse: ------------user:" + user.toString());
                Log.d(TAG, "onResponse: ----------header names:" + names);
            }

            @Override
            public void onFailure(Call<ItalkiResponse<User>> call, Throwable t) {
                Log.d(TAG, "onFailure: --------------");
                t.printStackTrace();

            }
        });
    }
}
