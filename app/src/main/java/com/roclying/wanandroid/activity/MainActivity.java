package com.roclying.wanandroid.activity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.roclying.wanandroid.R;
import com.roclying.wanandroid.fragment.MainFragment;
import com.roclying.wanandroid.fragment.MineFragment;
import com.roclying.wanandroid.fragment.MeizhiFragment;

import java.util.ArrayList;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton homeRbtn;
    private RadioButton videoRbtn;
    private RadioButton mineRbtn;
    private MainFragmentAdapter adapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int checkRbtnId;
    private int normalColor;
    private int selColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitile(getString(R.string.app_name));
        setBackBtnVisible(false);
        hideActionBar();
    }

    @Override
    void initView() {
        viewPager = findViewById(R.id.view_pager);
        radioGroup = findViewById(R.id.radio_group);
        homeRbtn = findViewById(R.id.rbtn_home);
        videoRbtn = findViewById(R.id.rbtn_video);
        mineRbtn = findViewById(R.id.rbtn_me);


    }

    @Override
    void initData() {

        normalColor = getResources().getColor(R.color.color_000000);
        selColor = getResources().getColor(R.color.color_1296db);


        adapter = new MainFragmentAdapter(getSupportFragmentManager());
        fragments.add(new MainFragment());
        fragments.add(new MeizhiFragment());
        fragments.add(new MineFragment());


        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        homeRbtn.setChecked(true);
        updateTab();

    }

    @Override
    void initListener() {
        radioGroup.setOnCheckedChangeListener(this);
        setOnLeftBtnClickListener(new OnLeftBtnClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });

    }

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbtn_home: {
                viewPager.setCurrentItem(0);

                break;
            }
            case R.id.rbtn_video: {
                viewPager.setCurrentItem(1);

                break;
            }
            case R.id.rbtn_me: {
                viewPager.setCurrentItem(2);

                break;
            }
        }
        checkRbtnId = checkedId;
        updateTab();
    }

    private void updateTab() {
        homeRbtn.setTextColor(homeRbtn.isChecked() ? selColor : normalColor);
        videoRbtn.setTextColor(videoRbtn.isChecked() ? selColor : normalColor);
        mineRbtn.setTextColor(mineRbtn.isChecked() ? selColor : normalColor);
    }


    public class MainFragmentAdapter extends FragmentPagerAdapter {

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
