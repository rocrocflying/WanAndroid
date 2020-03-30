package presenter;

import android.util.Log;
import android.widget.Toast;

import com.roclying.wanandroid.R;

import java.util.ArrayList;

import contract.MainContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import model.homepage.Article;
import network.ResposeBody;
import network.RetrofitManager;

public class HomePagePresenter implements MainContract.presenter {

    private MainContract.View view;
    private static final String TAG = "HomePagePresenter";

    public HomePagePresenter(MainContract.View view) {
        this.view = view;
    }


    @Override
    public ArrayList<Article.ArticleItem> getHomePageArticleList(int page, final boolean isRefresh) {

        Disposable disposable = RetrofitManager.getInstance().getRequest().getHomeArticle(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResposeBody<Article>>() {
                               @Override
                               public void accept(@io.reactivex.annotations.NonNull ResposeBody<Article> articles) throws Exception {
                                   for (int i = 0; i < articles.getData().getDatas().size(); i++) {
                                       Log.v(TAG, "第" + i + "个" + articles.getData().getDatas().get(i).toString() + "\n");
                                   }
                                   if (isRefresh) {
                                       view.refresh(articles.getData().getDatas());
                                       return;

                                   }
                                   view.loadMore(articles.getData().getDatas());

                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
//                                   Toast.makeText(getActivity(), R.string.app_name, Toast.LENGTH_SHORT).show();
                               }


                           }


                );
        return null;
    }

    @Override
    public void refresh() {
        getHomePageArticleList(0, true);

    }

    @Override
    public void loadMore(int page) {

        getHomePageArticleList(page, false);


    }


}
