package zyxd.fish.lib_common.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import zyxd.fish.lib_common.lifecycle.BaseViewModel

abstract class BaseLazyFragment<VM : BaseViewModel,DB : ViewDataBinding>
    : BaseFragment<VM,DB>(),LifecycleObserver{

    private var visibleToUser = false

    override fun onResume() {
        super.onResume()
        if (!visibleToUser){
            visibleToUser = true
            lazyLoad()
        }
    }

    /**
     * 懒加载，只有在Fragment第一次创建且第一次对用户可见
     */
    abstract fun lazyLoad()
}