package zyxd.fish.mvvmtest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import zyxd.fish.lib_common.base.BaseFragment
import zyxd.fish.mvvmtest.App
import zyxd.fish.mvvmtest.R
import zyxd.fish.mvvmtest.databinding.FragmentLinkageBinding
import zyxd.fish.mvvmtest.vm.LinkageViewModel

class LinkageFragment : BaseFragment<LinkageViewModel,FragmentLinkageBinding>(),SeekBar.OnSeekBarChangeListener {

    companion object{
        fun getInstance() : LinkageFragment{
            val bundle = Bundle()
            val linkageFragment = LinkageFragment()
            linkageFragment.arguments = bundle
            return linkageFragment
        }
    }

    override fun initViewModel(): LinkageViewModel {
        return ViewModelProvider(getActivity()?.viewModelStore!!,ViewModelProvider.AndroidViewModelFactory(App())).get(LinkageViewModel::class.java)
    }

    override fun initData() {
        dataBinding.model = viewModel
        dataBinding.lifecycleOwner = this
    }

    override fun initView() {
        dataBinding.skII.setOnSeekBarChangeListener(this)
    }

    override fun onCreate() = R.layout.fragment_linkage

    override fun showError(obj: Any?) {
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        viewModel.getProgress().value = p1
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }
}