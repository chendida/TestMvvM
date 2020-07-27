package zyxd.fish.lib_common.base;


import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import zyxd.fish.lib_common.bean.DialogBean;
import zyxd.fish.lib_common.lifecycle.BaseViewModel;

public abstract class BaseActivity<VM extends BaseViewModel,DB extends ViewDataBinding>
    extends BaseNoModelActivity<DB>{

    protected VM viewModel;

    @Override
    protected DB initDataBinding(int layoutId) {
        DB db =  super.initDataBinding(layoutId);
        /**
         * 将这两个初始化函数插在{@link BaseActivity#initDataBinding}
         */
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
            public void onChanged(Object o) {
                showError(o);
            }
        });
    }

    /**
     * ViewModel层发生了错误
     * @param o
     */
    protected abstract void showError(Object o);

    /**
     * 初始化ViewModel
     * @return
     */
    protected abstract VM initViewModel();
}
