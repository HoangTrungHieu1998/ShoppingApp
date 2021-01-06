package com.example.shoppingapp.view;

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

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapter.DetailAdapter;
import com.example.shoppingapp.model.Item;
import com.example.shoppingapp.viewmodel.ItemViewModel;

import java.util.List;

public class Headphone extends Fragment {

    ItemViewModel itemViewModel;
    RecyclerView mRcv;
    DetailAdapter mAdapter;
    List<Item> itemList;
    SwipeRefreshLayout mRefresh;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_headphone, container, false);

        // Ánh xạ
        mRcv = view.findViewById(R.id.recycleViewHeadphone);
        mRefresh = view.findViewById(R.id.pullRefreshHeadphone);

        // View model
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        itemViewModel.getDetail().observe(requireActivity(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                mAdapter.setItemList(items);
            }
        });
        itemViewModel.fetchDetail(3);

        // Set adapter and Recycle View
        mAdapter = new DetailAdapter(getContext(),itemList);
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
                itemViewModel.getDetail().observe(requireActivity(), new Observer<List<Item>>() {
                    @Override
                    public void onChanged(List<Item> items) {
                        mAdapter.setItemList(items);
                    }
                });
                itemViewModel.fetchDetail(3);
                mRefresh.setRefreshing(false);
            }
        });

        return view;
    }
}