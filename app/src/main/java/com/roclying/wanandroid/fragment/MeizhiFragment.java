package com.roclying.wanandroid.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roclying.wanandroid.R;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import adapter.HomePageAdapter;
import adapter.MeiziAdapter;
import contract.MeizhiContract;
import model.homepage.Meizhi;
import presenter.MeizhiPagerPresenter;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

public class MeizhiFragment extends Fragment implements MeizhiContract.meizhiView {

    private SmartRefreshLayout meiziRefreshLayout;
    private RecyclerView meiziRecylerView;
    private MeiziAdapter adapter;
    private ArrayList<Meizhi> meizhis = new ArrayList<>();
    private MeizhiPagerPresenter pagerPresenter;
    private int page = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View meiziView = inflater.inflate(R.layout.fragment_video_layout, null);
        return meiziView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        meiziRecylerView = view.findViewById(R.id.recyler_view_img);
        meiziRecylerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        meiziRefreshLayout = view.findViewById(R.id.smart_refresh_layout);
        meiziRefreshLayout.setEnableLoadMore(true);
        meiziRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        adapter = new MeiziAdapter(meizhis, getContext());
        meiziRecylerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pagerPresenter = new MeizhiPagerPresenter(this);

        initListener();

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            pagerPresenter.getMeizhList(1, true);
        }
    }

    private void initListener() {
        meiziRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagerPresenter.loadMore(++page);

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pagerPresenter.refresh();

            }
        });
    }

    @Override
    public void loadMore(ArrayList<Meizhi> meizhiArrayList) {
        meizhis.addAll(meizhiArrayList);
        adapter.notifyDataSetChanged();
        meiziRefreshLayout.finishLoadMore();
    }

    @Override
    public void refresh(ArrayList<Meizhi> meizhiArrayList) {
        if (meizhis != null && meizhis.size() > 0) {
            meizhis.clear();
        }
        meizhis.addAll(meizhiArrayList);
        adapter.notifyDataSetChanged();
        meiziRefreshLayout.finishRefresh();
    }

    @Override
    public void showLoadDialog() {

    }
}
