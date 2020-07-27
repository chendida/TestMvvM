package zyxd.fish.mvvmtest.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import zyxd.fish.lib_common.lifecycle.BaseViewModel
import zyxd.fish.mvvmtest.api.Api
import zyxd.fish.mvvmtest.bean.JueJinBean

class LazyViewModel : BaseViewModel() {
    private val jueJin = MutableLiveData<JueJinBean>()

    fun loadData(category : String){
        showDialog.setValue(true,"懒加载中...")
        val disposable = Api.getInstance().jueJin(category, "20", "android")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                jueJin.value = it
                Log.i("data666",it.toString())
                showDialog.setValue(false)
            }, {
                Log.i("data6666",it.message.toString())
                showDialog.setValue(false)
                /**
                 * 通知UI层
                 */
                error.setValue("发生错误了")
            })
        addDisposable(disposable)
    }

    fun getJueJin() : MutableLiveData<JueJinBean>{
        return jueJin
    }
}