package zyxd.fish.mvvmtest.ui.fragment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import zyxd.fish.lib_common.base.BaseLazyFragment
import zyxd.fish.mvvmtest.App
import zyxd.fish.mvvmtest.R
import zyxd.fish.mvvmtest.adapter.AndroidAdapter
import zyxd.fish.mvvmtest.bean.JueJinBean
import zyxd.fish.mvvmtest.databinding.FragmentLazyBinding
import zyxd.fish.mvvmtest.vm.LazyViewModel

class LazyFragment : BaseLazyFragment<LazyViewModel,FragmentLazyBinding>() {

    companion object{
        fun getInstance(category : String) : LazyFragment{
            val bundle = Bundle()
            bundle.putString("category",category)
            val lazyFragment = LazyFragment()
            lazyFragment.arguments = bundle
            return lazyFragment
        }
    }

    private var category = ""

    private val mAdapter by lazy {
        AndroidAdapter()
    }

    override fun lazyLoad() {
        viewModel.loadData(category)
    }

    override fun initViewModel(): LazyViewModel {
        return ViewModelProvider.AndroidViewModelFactory(App()).create(LazyViewModel::class.java)
    }

    override fun initData() {
        category = arguments?.get("category").let {
            it as String
        }
        viewModel.getJueJin().observe(this,
            Observer<JueJinBean> {
                mAdapter.addData(it.d.entrylist)
                Log.i("data666","size=${mAdapter.itemCount}")
            })
    }

    override fun initView() {
        val linearLayoutManager = LinearLayoutManager(context)
        dataBinding.rvList.run {
            isNestedScrollingEnabled = true
            adapter = mAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }
    }

    override fun onCreate(): Int = R.layout.fragment_lazy

    override fun showError(obj: Any?) {
        Toast.makeText(context,"报错啦",Toast.LENGTH_SHORT).show()
    }
}