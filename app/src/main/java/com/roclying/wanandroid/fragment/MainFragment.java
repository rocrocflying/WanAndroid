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
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.footer.FalsifyFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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
import utils.WParam;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;

public class MainFragment extends BaseFragment implements MainContract.View {

    private static final String TAG = "MainFragment";
    private SmartRefreshLayout smartRefreshLayout;
    private RecyclerView recyclerView;
    private HomePageAdapter adapter;
    private ArrayList<Article.ArticleItem> articleItems = new ArrayList<>();
    private int page;
    private HomePagePresenter pagePresenter;
    private boolean hasLoadData = false;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean isNightMode = WParam.getBoolean(WParam.NIGHT_MODE);
        recyclerView = view.findViewById(R.id.recyler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        smartRefreshLayout = view.findViewById(R.id.smart_refresh_layout);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setRefreshHeader(isNightMode?new BezierRadarHeader(getContext()):new BezierCircleHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setAnimatingColor(getResources().getColor(R.color.color_1296db)));
        adapter = new HomePageAdapter(getContext(), articleItems);
        recyclerView.setAdapter(adapter);

    }

    private void initListener() {
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagePresenter.loadMore(++page);

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pagePresenter.refresh();

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pagePresenter = new HomePagePresenter(this);
        if (!hasLoadData) {
            pagePresenter.getHomePageArticleList(0, true);
            hasLoadData = true;
        }
        initListener();

    }

    @Override
    int getLayout() {
        return R.layout.fragment_home_layout;
    }

    @Override
    String getTitle() {
        return getString(R.string.title_tab_home);
    }

    @Override
    public int getPage() {
        return 0;
    }

    @Override
    public void refresh(ArrayList<Article.ArticleItem> list) {
        if (articleItems != null && articleItems.size() > 0) {
            articleItems.clear();
        }
        smartRefreshLayout.autoRefresh();
        articleItems.addAll(list);
        adapter.notifyDataSetChanged();
        smartRefreshLayout.finishRefresh(1000);


    }

    @Override
    public void loadMore(ArrayList<Article.ArticleItem> list) {
        articleItems.addAll(list);
        adapter.notifyDataSetChanged();
        smartRefreshLayout.finishLoadMore();
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
