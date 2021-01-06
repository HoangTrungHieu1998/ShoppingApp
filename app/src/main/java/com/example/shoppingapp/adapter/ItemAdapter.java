package com.example.shoppingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shoppingapp.R;
import com.example.shoppingapp.model.Item;
import com.example.shoppingapp.view.OnItemListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    Context context;
    List<Item> itemList;
    OnItemListener onItemListener;

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.mTxtName.setText(item.getItemname());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.mTxtPrice.setText("Giá: "+ decimalFormat.format(item.getItemprice())+" Đ");
        Glide.with(context)
                .load(item.getItemimage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.pokemon)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.mImgItem);

    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mImgItem;
        TextView mTxtName, mTxtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgItem = itemView.findViewById(R.id.imgItem);
            mTxtName = itemView.findViewById(R.id.itemName);
            mTxtPrice = itemView.findViewById(R.id.itemPrice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    onItemListener.OnClick(itemList.get(position));
                }
            });
        }
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }
}
