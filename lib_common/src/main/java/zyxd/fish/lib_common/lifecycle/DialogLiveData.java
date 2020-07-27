package zyxd.fish.lib_common.lifecycle;

import androidx.lifecycle.MutableLiveData;

import zyxd.fish.lib_common.bean.DialogBean;

public final class DialogLiveData<T> extends MutableLiveData<T> {
    private DialogBean bean = new DialogBean();

    public void setValue(boolean isShow){
        bean.setShow(isShow);
        bean.setMsg("");
        setValue((T) bean);
    }


    public void setValue(boolean isShow,String msg){
        bean.setShow(isShow);
        bean.setMsg(msg);
        setValue((T) bean);
    }
}
