package contract;

import com.roclying.wanandroid.activity.IView;

import java.util.ArrayList;

import model.homepage.Article;

public interface MainContract {

    interface View extends IView {

        int getPage();

        void refresh(ArrayList<Article.ArticleItem> list);

        void loadMore(ArrayList<Article.ArticleItem> list);

        void showHomePageArticleList(ArrayList<Article.ArticleItem> list);

    }

    interface presenter {

        ArrayList<Article.ArticleItem> getHomePageArticleList(int page,boolean isRefresh);

        void refresh();

        void loadMore(int page);

    }
}
