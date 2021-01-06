package com.example.shoppingapp.api;

import com.example.shoppingapp.model.Guest;
import com.example.shoppingapp.model.Item;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {

    @GET("shop/getItem.php")
    Maybe<List<Item>> fetchItem();

    @FormUrlEncoded
    @POST("shop/getDetail.php")
    Maybe<List<Item>> fetchDetail(@Field("itemid") Integer itemid);

    @FormUrlEncoded
    @POST("shop/guestInfo.php")
    Maybe<List<Guest>> insertGuest( @Field("guestname") String guestname ,
                                    @Field("guestphone") Integer guestphone ,
                                    @Field("guestemail") String guestemail );
}
