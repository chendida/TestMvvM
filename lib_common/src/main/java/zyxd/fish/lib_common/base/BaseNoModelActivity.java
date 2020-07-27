package zyxd.fish.lib_common.base;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import zyxd.fish.lib_common.utils.ActivityUtil;
import zyxd.fish.lib_common.view.LoadingDialog;

public abstract class BaseNoModelActivity<DB extends ViewDataBinding> extends AppCompatActivity {
    protected DB dataBinding;

    protected Context context;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ActivityUtil.getInstance().addActivity(this);
        int layoutId = onCreate();
        setContentView(layoutId);

        dataBinding = initDataBinding(layoutId);
        initView();
        initData();
    }

    /**
     * 初始化要加载的布局资源ID
     * 此函数优先执行于onCreate()可以做window操作
     */
    protected abstract int onCreate();

    /**
     * 初始化DataBinding
     * @param layoutId
     * @return
     */
    protected DB initDataBinding(@LayoutRes int layoutId){
        return DataBindingUtil.setContentView(this,layoutId);
    }

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dataBinding != null){
            dataBinding.unbind();
        }
        ActivityUtil.getInstance().removeActivity(this);
    }

    /**
     * 显示等待提示框
     * @param loadMsg
     */
    protected void showDialog(String loadMsg){
        if (loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.setWaitHintMsg(loadMsg);
        }else {
            loadingDialog = new LoadingDialog(this);
            loadingDialog.setWaitHintMsg(loadMsg);
            loadingDialog.show();
        }
    }

    /**
     * 隐藏等待提示框
     */
    protected void dismissDialog(){
        if (loadingDialog != null && loadingDialog.isShowing()){
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }
}
