package com.example.shoppingapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shoppingapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class Contact extends Fragment {

    CircleImageView circleImageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        // Ánh xạ
        circleImageView = view.findViewById(R.id.circleView);

        Picasso.with(getContext()).load("https://scontent-hkg4-2.xx.fbcdn.net/v/t1.0-9/64782552_2442942389269365_7535812522205511680_o.jpg?_nc_cat=104&ccb=2&_nc_sid=09cbfe&_nc_ohc=rHEVHvLeo_8AX-1naVJ&_nc_ht=scontent-hkg4-2.xx&oh=f805e5e3096a1f88cf3c7d625e5e808a&oe=6019B561")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.pokemon)
                .into(circleImageView);


        return view;
    }
}