package com.example.shoppingapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.shoppingapp.R;
import com.example.shoppingapp.model.Payment;
import com.example.shoppingapp.view.MainActivity;
import com.example.shoppingapp.view.OnItemListener;
import com.example.shoppingapp.view.OnPaymentListener;
import com.example.shoppingapp.view.PaymentActivity;

import java.text.DecimalFormat;
import java.util.List;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {

    OnPaymentListener onPaymentListener;
    OnItemListener onItemListener;
    Context context;
    List<Payment> paymentList;

    public PaymentAdapter(Context context, List<Payment> paymentList) {
        this.context = context;
        this.paymentList = paymentList;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_payment, parent, false);
        return new PaymentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        Payment payment = paymentList.get(position);
        holder.txtNamePayment.setText(payment.getItemName());
        //holder.txtNumber.setText("Số lượng: "+ String.valueOf(payment.getItemNumber()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtPricePayment.setText(decimalFormat.format(payment.getPrice())+" Đ");
        holder.btnText.setText(String.valueOf(payment.getItemNumber()));
        Glide.with(context).load(payment.getItemImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.pokemon)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.mImgPayment);

        // Check condition
        int num = Integer.parseInt(holder.btnText.getText().toString());
        if(num >=10){
            holder.btnPlus.setVisibility(View.INVISIBLE);
            holder.btnMinus.setVisibility(View.VISIBLE);
        }else if(num <=1){
            holder.btnMinus.setVisibility(View.INVISIBLE);
        }else{
            holder.btnMinus.setVisibility(View.VISIBLE);
            holder.btnPlus.setVisibility(View.VISIBLE);
        }

        // Set even button plus
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newNum = Integer.parseInt(holder.btnText.getText().toString())+1;
                int nowNum = payment.getItemNumber();
                int price = payment.getPrice();
                payment.setItemNumber(newNum);
                int newPrice = (newNum*price)/nowNum;
                payment.setPrice(newPrice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.txtPricePayment.setText(decimalFormat.format(newPrice)+" Đ");
                PaymentActivity.Total();
                if(newNum >9){
                    holder.btnPlus.setVisibility(View.INVISIBLE);
                    holder.btnMinus.setVisibility(View.VISIBLE);
                    holder.btnText.setText(String.valueOf(newNum));
                }else {
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinus.setVisibility(View.VISIBLE);
                    holder.btnText.setText(String.valueOf(newNum));
                }
            }
        });

        // Set even button minus
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newNum = Integer.parseInt(holder.btnText.getText().toString())-1;
                int nowNum = payment.getItemNumber();
                int price = payment.getPrice();
                payment.setItemNumber(newNum);
                int newPrice = (newNum*price)/nowNum;
                payment.setPrice(newPrice);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.txtPricePayment.setText(decimalFormat.format(newPrice)+" Đ");
                PaymentActivity.Total();
                if(newNum <2){
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinus.setVisibility(View.INVISIBLE);
                    holder.btnText.setText(String.valueOf(newNum));
                }else {
                    holder.btnPlus.setVisibility(View.VISIBLE);
                    holder.btnMinus.setVisibility(View.VISIBLE);
                    holder.btnText.setText(String.valueOf(newNum));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return paymentList == null ? 0:paymentList.size();
    }

    class PaymentViewHolder extends RecyclerView.ViewHolder{

        ImageView mImgPayment;
        TextView txtNamePayment, txtPricePayment;
        Button btnMinus,btnText,btnPlus;
        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgPayment = itemView.findViewById(R.id.imgPayment);
            txtNamePayment = itemView.findViewById(R.id.txtNamePayment);
            txtPricePayment = itemView.findViewById(R.id.txtPricePayment);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnText = itemView.findViewById(R.id.btnText);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onPaymentListener.OnLongClick(getAdapterPosition());
                    return false;
                }
            });
        }
    }

    public void setOnPaymentListener(OnPaymentListener onPaymentListener) {
        this.onPaymentListener = onPaymentListener;
    }
}
