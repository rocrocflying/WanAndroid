package contract;

import com.roclying.wanandroid.activity.IView;

import java.util.ArrayList;

import model.homepage.Article;
import model.homepage.Banner;

public interface MainContract {

    interface View extends IView {

        int getPage();

        void refresh(ArrayList<Article.ArticleItem> list);

        void loadMore(ArrayList<Article.ArticleItem> list);

        void showBanner(ArrayList<Banner> banners);

        void showHomePageArticleList(ArrayList<Article.ArticleItem> list);

    }

    interface presenter {

        ArrayList<Article.ArticleItem> getHomePageArticleList(int page, boolean isRefresh);

        ArrayList<Banner> getBanners();

        void refresh();

        void loadMore(int page);

    }
}
