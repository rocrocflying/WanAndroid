package contract;

import com.roclying.wanandroid.activity.IPresenter;
import com.roclying.wanandroid.activity.IView;

import java.util.ArrayList;

import model.homepage.Article;
import model.homepage.Meizhi;

public interface MeizhiContract {

    interface meizhiView extends IView {
        void loadMore(ArrayList<Meizhi> meizhiArrayList);

        void refresh(ArrayList<Meizhi> meizhiArrayList);
    }

    interface meizhiPresenter extends IPresenter {

        ArrayList<Meizhi> getMeizhList(int page, boolean isRefresh);

        void refresh();

        void loadMore(int page);
    }
}
