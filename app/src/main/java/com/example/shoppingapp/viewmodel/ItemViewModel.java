package com.example.shoppingapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppingapp.model.Guest;
import com.example.shoppingapp.model.Item;
import com.example.shoppingapp.repository.ItemRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ItemViewModel extends ViewModel {

    private ItemRepository itemRepository;
    private MutableLiveData<List<Item>> mGetItem;
    private MutableLiveData<List<Item>> mGetDetail;
    private MutableLiveData<List<Guest>> mInsertGuest;

    public ItemViewModel() {
        mGetItem = new MutableLiveData<>();
        mGetDetail = new MutableLiveData<>();
        mInsertGuest = new MutableLiveData<>();
        itemRepository = ItemRepository.getInstance();
    }

    public void fetchItem(){
        // Sử dụng rxjava và rxandrod
        itemRepository.getItem()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Item>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Item> items) {
                        mGetItem.setValue(items);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("BBB",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public LiveData<List<Item>> getItems(){
        return mGetItem;
    }

    public void fetchDetail(Integer itemid){
        itemRepository.getDetail(itemid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Item>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Item> items) {
                        mGetDetail.setValue(items);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public LiveData<List<Item>> getDetail(){
        return mGetDetail;
    }

    public void insertGuest(String guestname, Integer guestphone, String guestemail){
        itemRepository.insertGuests(guestname,guestphone,guestemail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<List<Guest>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Guest> guests) {
                        mInsertGuest.setValue(guests);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public LiveData<List<Guest>>getGuest(){
        return mInsertGuest;
    }
}
