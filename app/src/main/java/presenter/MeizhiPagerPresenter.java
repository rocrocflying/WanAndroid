package presenter;

import android.util.Log;

import java.util.ArrayList;

import contract.MeizhiContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import model.homepage.Meizhi;
import network.MeizhiRequestBody;
import network.ResposeBody;
import network.RetrofitManager;

public class MeizhiPagerPresenter implements MeizhiContract.meizhiPresenter {

    private MeizhiContract.meizhiView meizhiView;
    private static final String TAG = "MeizhiPagerPresenter";

    public MeizhiPagerPresenter(MeizhiContract.meizhiView meizhiView) {
        this.meizhiView = meizhiView;
    }

    @Override
    public ArrayList<Meizhi> getMeizhList(int page, final boolean isRefresh) {
        Disposable meizhiDisposable = RetrofitManager.getInstance().getMeizhiRequest().getMeizhiList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MeizhiRequestBody>() {
                    @Override
                    public void accept(MeizhiRequestBody meizhiResposeBody) throws Exception {
                        Log.v(TAG, "meizhiResposeBody:" + meizhiResposeBody.data.size());

                        if (isRefresh) {
                            meizhiView.refresh(meizhiResposeBody.data);
                            return;
                        }
                        meizhiView.loadMore(meizhiResposeBody.data);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.v(TAG, "meizhi get error" + throwable.getMessage());
                    }
                });

        return null;
    }

    @Override
    public void refresh() {

        getMeizhList(0, true);

    }

    @Override
    public void loadMore(int page) {
        getMeizhList(page, false);
    }
}
