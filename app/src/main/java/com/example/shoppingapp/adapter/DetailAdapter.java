package com.example.shoppingapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shoppingapp.R;
import com.example.shoppingapp.model.Item;
import com.example.shoppingapp.view.OnItemListener;

import java.text.DecimalFormat;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {

    Context context;
    List<Item> itemList;
    OnItemListener onItemListener;

    public DetailAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_detail, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.mTxtNameDetail.setText(item.getItemname());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.mTxtPriceDetail.setText(decimalFormat.format(item.getItemprice())+" Đ");
        Glide.with(context).load(item.getItemimage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.pokemon)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.mImgItemDetail);

    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0:itemList.size();
    }

    class DetailViewHolder extends RecyclerView.ViewHolder{

        ImageView mImgItemDetail;
        TextView mTxtNameDetail, mTxtPriceDetail;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgItemDetail = itemView.findViewById(R.id.imgItemDetail);
            mTxtNameDetail = itemView.findViewById(R.id.txtNameDetail);
            mTxtPriceDetail = itemView.findViewById(R.id.txtPriceDetail);
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
