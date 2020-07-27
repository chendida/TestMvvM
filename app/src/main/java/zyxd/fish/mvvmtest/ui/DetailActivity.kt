package zyxd.fish.mvvmtest.ui

import android.text.TextUtils
import zyxd.fish.lib_common.base.BaseNoModelActivity
import zyxd.fish.mvvmtest.R
import zyxd.fish.mvvmtest.databinding.ActivityDetailBinding
import zyxd.fish.mvvmtest.ui.fragment.DetailFragment

class DetailActivity : BaseNoModelActivity<ActivityDetailBinding>() {

    override fun onCreate() = R.layout.activity_detail

    override fun initView() {
        var url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        if (TextUtils.isEmpty(url)) {
            url = "https://www.baidu.com"
            setTitle("Fragment使用示例")
        } else {
            setTitle(title)
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_content, DetailFragment.getInstance(url))
            .commit()
    }

    override fun initData() {
    }
}
