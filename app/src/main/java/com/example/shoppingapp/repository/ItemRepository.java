package com.example.shoppingapp.repository;

import com.example.shoppingapp.api.ApiRequest;
import com.example.shoppingapp.api.RetrofitInit;
import com.example.shoppingapp.model.Guest;
import com.example.shoppingapp.model.Item;

import java.util.List;

import io.reactivex.rxjava3.core.Maybe;

public class ItemRepository {
    private static ItemRepository mInstance = null;
    private ApiRequest apiRequest = null;

    private ItemRepository(){
        apiRequest = RetrofitInit.getInstance();
    }

    public static ItemRepository getInstance(){
        if(mInstance==null){
            mInstance = new ItemRepository();
        }
        return mInstance;
    }

    public Maybe<List<Item>> getItem(){
        return apiRequest.fetchItem();
    }

    public Maybe<List<Item>> getDetail(Integer itemid){
        return apiRequest.fetchDetail(itemid);
    }

    public Maybe<List<Guest>> insertGuests(String guestname, Integer guestphone, String guestemail){
        return apiRequest.insertGuest(guestname,guestphone,guestemail);
    }
}
