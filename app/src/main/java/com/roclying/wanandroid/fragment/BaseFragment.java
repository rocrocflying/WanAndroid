package com.roclying.wanandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.roclying.wanandroid.R;

public abstract class BaseFragment extends Fragment {

    private TextView titleTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout rootView = new LinearLayout(getContext());
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.setBackgroundColor(getResources().getColor(R.color.color_fragment_header));
        rootView.setOrientation(LinearLayout.VERTICAL);
        View headerView = inflater.inflate(R.layout.header_fragment_layout, null);
        titleTv = headerView.findViewById(R.id.tv_title);
        View content = inflater.inflate(getLayout(), null);
        rootView.addView(headerView);
        rootView.addView(content);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titleTv.setText(getTitle());
    }

    abstract int getLayout();

    abstract String getTitle();
}
