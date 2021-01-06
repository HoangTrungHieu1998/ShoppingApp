package com.example.shoppingapp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapter.ItemAdapter;
import com.example.shoppingapp.model.Item;
import com.example.shoppingapp.viewmodel.ItemViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment {

    ViewFlipper viewFlipper;
    RecyclerView mRcv;
    ItemAdapter mAdapter;
    SwipeRefreshLayout mRefresh;
    ItemViewModel itemViewModel;
    List<Item> itemList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Ánh xạ
        mRcv = view.findViewById(R.id.recycleView);
        mRefresh = view.findViewById(R.id.pullRefresh);
        viewFlipper =view.findViewById(R.id.viewFlipper);

        // ViewFlipper
        ActionViewFlipper();

        // View Model
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        itemViewModel.getItems().observe(requireActivity(), new Observer<List<Item>>(){
            @Override
            public void onChanged(List<Item> items) {
                mAdapter.setItemList(items);
            }
        });
        itemViewModel.fetchItem();

        // Set adapter and Recycle View
        mAdapter = new ItemAdapter(getContext(),itemList);
        mRcv.setHasFixedSize(true);
        mRcv.setAdapter(mAdapter);

        // Set Even Click Item
        mAdapter.setOnItemListener(new OnItemListener() {
            @Override
            public void OnClick(Item item) {
                Intent intent = new Intent(requireActivity(),DetailActivity.class);
                intent.putExtra("image",item.getItemimage());
                intent.putExtra("price",item.getItemprice());
                intent.putExtra("name",item.getItemname());
                intent.putExtra("describe",item.getItemdescribe());
                intent.putExtra("id",item.getId());
                startActivity(intent);
            }
        });

        // Pull to Refresh
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                itemViewModel.getItems().observe(requireActivity(), new Observer<List<Item>>(){
                    @Override
                    public void onChanged(List<Item> items) {
                        mAdapter.setItemList(items);
                    }
                });
                itemViewModel.fetchItem();
                mRefresh.setRefreshing(false);
            }
        });

        return view;
    }

    private void ActionViewFlipper() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("https://cdn.tgdd.vn/2020/12/banner/xa-kho-laptop-800-300-800x300-1.png");
        arrayList.add("https://cdn.tgdd.vn/2020/12/banner/M51-800-300-800x300.png");
        arrayList.add("https://cdn.tgdd.vn/2020/12/banner/800-300-800x300-15.png");
        arrayList.add("https://cdn.tgdd.vn/2020/12/banner/reno5-800-300-800x300-1.png");
        for (int i = 0; i <arrayList.size() ; i++) {
            ImageView imageView = new ImageView(getContext());
            Picasso.with(getContext()).load(arrayList.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation animation_out = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setOutAnimation(animation_out);
    }
}