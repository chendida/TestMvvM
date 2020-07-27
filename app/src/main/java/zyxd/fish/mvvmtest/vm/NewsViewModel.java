package zyxd.fish.mvvmtest.vm;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import zyxd.fish.lib_common.lifecycle.BaseViewModel;
import zyxd.fish.mvvmtest.api.Api;
import zyxd.fish.mvvmtest.bean.NewsBean;

public class NewsViewModel extends BaseViewModel {
    /**
     * 当数据请求成功回调
     */
    protected MutableLiveData<NewsBean>news = new MutableLiveData<>();

    /**
     * 请求网络数据
     */
    public void requestData(){
        showDialog.setValue(true,"加载中...");
        Disposable disposable = Api.getInstance().news().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewsBean>() {
                    @Override
                    public void accept(NewsBean newsBean) throws Exception {
                        showDialog.setValue(false);
                        news.setValue(newsBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showDialog.setValue(false);
                    }
                });
        addDisposable(disposable);
    }

    public MutableLiveData<NewsBean> getNews(){
        return news;
    }
}
