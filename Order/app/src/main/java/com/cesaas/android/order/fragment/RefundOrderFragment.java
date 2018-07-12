package com.cesaas.android.order.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cesaas.android.order.R;


/**
 * Author FGB
 * Description
 * Created 2017/4/2 0:49
 * Version 1.0
 */
public class RefundOrderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_games,container,false);
        return view;
    }
}
