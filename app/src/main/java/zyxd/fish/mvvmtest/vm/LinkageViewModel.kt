package zyxd.fish.mvvmtest.vm

import androidx.lifecycle.MutableLiveData
import zyxd.fish.lib_common.lifecycle.BaseViewModel

class LinkageViewModel : BaseViewModel() {
    private val progress = MutableLiveData<Int>()


    fun getProgress() : MutableLiveData<Int>{
        return progress
    }
}