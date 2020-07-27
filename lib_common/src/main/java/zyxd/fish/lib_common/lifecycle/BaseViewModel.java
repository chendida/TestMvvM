package zyxd.fish.lib_common.lifecycle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import zyxd.fish.lib_common.bean.DialogBean;

public abstract class BaseViewModel extends ViewModel {
    /**
     *管理RxJava的请求
     */
    private CompositeDisposable compositeDisposable;

    /**
     * 通知Activity/Fragment  是否显示等待Dialog
     */
    protected DialogLiveData<DialogBean> showDialog = new DialogLiveData<>();

    /**
     * 当ViewModel层出现错误通知Activity/Fragment
     */
    protected MutableLiveData<Object>error = new MutableLiveData<>();

    /**
     * 添加rxJava发出的请求
     * @param disposable
     */
    protected void addDisposable(Disposable disposable){
        if (compositeDisposable == null || compositeDisposable.isDisposed()){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void getShowDialog(LifecycleOwner lifecycleOwner, Observer<DialogBean> observer){
        showDialog.observe(lifecycleOwner,observer);
    }

    public void getError(LifecycleOwner lifecycleOwner,Observer<Object>observer){
        error.observe(lifecycleOwner,observer);
    }

    /**
     * ViewModel销毁的同时取消网络请求
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null){
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        showDialog = null;
        error = null;
    }
}
