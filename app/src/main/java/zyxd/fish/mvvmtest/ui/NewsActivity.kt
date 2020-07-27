package zyxd.fish.mvvmtest.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import zyxd.fish.lib_common.base.BaseActivity
import zyxd.fish.mvvmtest.App
import zyxd.fish.mvvmtest.R
import zyxd.fish.mvvmtest.adapter.NewsAdapter
import zyxd.fish.mvvmtest.bean.NewsBean
import zyxd.fish.mvvmtest.databinding.ActivityNewsBinding
import zyxd.fish.mvvmtest.vm.NewsViewModel

class NewsActivity : BaseActivity<NewsViewModel,ActivityNewsBinding>() {

    override fun onCreate(): Int = R.layout.activity_news

    private val mAdapter by lazy {
        NewsAdapter()
    }

    override fun initView() {
        val rvNews = dataBinding.rvNews
        val mLinearLayoutManager = LinearLayoutManager(this)
        rvNews.run {
            isNestedScrollingEnabled = true
            adapter = mAdapter
            layoutManager = mLinearLayoutManager
            setHasFixedSize(true)
        }
    }

    override fun initData() {
        dataBinding.model = this
        viewModel.news.observe(this,
            Observer<NewsBean> {
                mAdapter.addData(it.stories)
            })
    }

    override fun showError(o: Any?) {
    }

    override fun initViewModel(): NewsViewModel {
        Log.i("App","initViewModel")
        return ViewModelProvider.AndroidViewModelFactory(App()).create(NewsViewModel::class.java)
    }

    fun requestData(view: View){
        viewModel.requestData()
    }
}
