package contract;

import com.roclying.wanandroid.activity.IView;

import java.util.ArrayList;

import model.homepage.Article;

public interface MainContract {

    interface View extends IView {

        void showHomePageArticleList(ArrayList<Article.ArticleItem> list);

    }

    interface presenter {

        ArrayList<Article.ArticleItem> getHomePageArticleList();
    }
}
