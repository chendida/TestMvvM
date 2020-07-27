package zyxd.fish.mvvmtest.ui

import android.widget.SeekBar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import zyxd.fish.lib_common.base.BaseActivity
import zyxd.fish.mvvmtest.App
import zyxd.fish.mvvmtest.R
import zyxd.fish.mvvmtest.databinding.ActivityLinkageBinding
import zyxd.fish.mvvmtest.ui.fragment.LinkageFragment
import zyxd.fish.mvvmtest.vm.LinkageViewModel

class LinkageActivity : BaseActivity<LinkageViewModel,ActivityLinkageBinding>()
    ,SeekBar.OnSeekBarChangeListener {

    override fun onCreate(): Int = R.layout.activity_linkage

    override fun initView() {
        title = "Activity/Fragment数据联动"
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_content,LinkageFragment.getInstance())
            .commit()
        dataBinding.skI.setOnSeekBarChangeListener(this)
    }

    override fun initData() {
        dataBinding.model = viewModel
        dataBinding.lifecycleOwner = this
    }

    override fun showError(o: Any?) {
    }

    override fun initViewModel(): LinkageViewModel {
        return ViewModelProvider(viewModelStore,ViewModelProvider.AndroidViewModelFactory(App())).get(LinkageViewModel::class.java)
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        viewModel.getProgress().value = p1
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}
