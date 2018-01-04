package com.example.alex.test_italki;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by alex on 18-1-4.
 */

public interface ItalkiService {


    /**
     * loginRxJava
     **/
    @FormUrlEncoded
    @POST("/api/login")
    Observable<ItalkiResponse<User>> loginRxJava(@Field(ItalkiApi.PARAM_LOGIN_USERNAME) String username,
                                                 @Field(ItalkiApi.PARAM_LOGIN_PASSWORD) String password);

    @FormUrlEncoded
    @POST("/api/login")
    Flowable<ItalkiResponse<User>> loginRxJava2(@Field(ItalkiApi.PARAM_LOGIN_USERNAME) String username,
                                                @Field(ItalkiApi.PARAM_LOGIN_PASSWORD) String password);

    @FormUrlEncoded
    @POST("/api/login")
    Flowable<Response<ItalkiResponse<User>>> loginRxJava2GetHeaders(@Field(ItalkiApi.PARAM_LOGIN_USERNAME) String username,
                                                                    @Field(ItalkiApi.PARAM_LOGIN_PASSWORD) String password);

    @FormUrlEncoded
    @POST("/api/login")
    Call<ItalkiResponse<User>> loginRetrofit(@Field(ItalkiApi.PARAM_LOGIN_USERNAME) String username,
                                             @Field(ItalkiApi.PARAM_LOGIN_PASSWORD) String password);

}
