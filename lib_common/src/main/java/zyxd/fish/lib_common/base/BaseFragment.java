package zyxd.fish.lib_common.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import zyxd.fish.lib_common.bean.DialogBean;
import zyxd.fish.lib_common.lifecycle.BaseViewModel;

public abstract class BaseFragment<VM extends BaseViewModel,DB extends ViewDataBinding> extends
    BaseNoModelFragment<DB>{

    protected VM viewModel;

    @Override
    public DB initDataBinding(LayoutInflater inflater, int layoutId, ViewGroup container) {
        DB db =  super.initDataBinding(inflater, layoutId, container);
        viewModel = initViewModel();
        initObserve();
        return db;
    }

    /**
     * 监听当前ViewModel中 showDialog和error的值
     */
    private void initObserve() {
        if (viewModel == null) return;
        viewModel.getShowDialog(this, new Observer<DialogBean>() {
            @Override
            public void onChanged(DialogBean dialogBean) {
                if (dialogBean.isShow()){
                    showDialog(dialogBean.getMsg());
                }else {
                    dismissDialog();
                }
            }
        });
        viewModel.getError(this, new Observer<Object>() {
            @Override
            public void onChanged(Object obj) {
                showError(obj);
            }
        });
    }

    /**
     * ViewModel层发生的错误
     * @param obj
     */
    protected abstract void showError(Object obj);


    /**
     * 初始化ViewModel
     * @return
     */
    protected abstract VM initViewModel();
}
