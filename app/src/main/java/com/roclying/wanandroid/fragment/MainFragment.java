package com.roclying.wanandroid.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roclying.wanandroid.R;
import com.scwang.smartrefresh.header.BezierCircleHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;

import java.util.ArrayList;

import adapter.HomePageAdapter;
import contract.MainContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import model.homepage.Article;
import network.ResposeBody;
import network.RetrofitManager;
import presenter.HomePagePresenter;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

public class MainFragment extends Fragment implements MainContract.View {

    private static final String TAG = "MainFragment";
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private HomePageAdapter adapter;
    private ArrayList<Article.ArticleItem> articleItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_home_layout, null);
        return mainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        smartRefreshLayout = view.findViewById(R.id.smart_refresh_layout);
        smartRefreshLayout.setRefreshHeader(new PhoenixHeader(getContext()));
        adapter = new HomePageAdapter(getContext(), articleItems);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HomePagePresenter pagePresenter = new HomePagePresenter(this);
        pagePresenter.getHomePageArticleList();

    }

    @Override
    public void showHomePageArticleList(ArrayList<Article.ArticleItem> list) {
        articleItems.addAll(list);
        adapter.notifyDataSetChanged();


    }

    @Override
    public void showLoadDialog() {

    }
}
