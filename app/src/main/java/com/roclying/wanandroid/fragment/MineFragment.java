package com.roclying.wanandroid.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.roclying.wanandroid.R;
import com.roclying.wanandroid.activity.AboutActivity;
import com.roclying.wanandroid.activity.MainActivity;

import utils.WParam;

public class MineFragment extends BaseFragment {

    private Switch nightModeSwitch;
    private TextView nightModeTv;
    private Activity activity;
    private RelativeLayout aboutLayout;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        activity = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nightModeSwitch = view.findViewById(R.id.switch_nightmode);
        nightModeTv = view.findViewById(R.id.tv_nightmode);
        aboutLayout = view.findViewById(R.id.rl_about);
        initListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        boolean isNightMode = WParam.getBoolean(WParam.NIGHT_MODE);
        nightModeSwitch.setChecked(isNightMode);
        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    WParam.putBoolean(WParam.NIGHT_MODE, true);

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    WParam.putBoolean(WParam.NIGHT_MODE, false);

                }
                getActivity().finish();
                getContext().startActivity(new Intent(getActivity(), MainActivity.class));
            }

        });

    }

    @Override
    int getLayout() {
        return R.layout.fragment_mine_layout;
    }

    @Override
    String getTitle() {
        return getString(R.string.title_tab_me);
    }

    @Override
    void initListener() {
        aboutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });
    }
}
