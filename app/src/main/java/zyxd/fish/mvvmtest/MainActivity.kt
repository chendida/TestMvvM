package zyxd.fish.mvvmtest

import android.content.Intent
import android.util.Log
import android.view.View
import zyxd.fish.lib_common.base.BaseNoModelActivity
import zyxd.fish.mvvmtest.databinding.ActivityMainBinding
import zyxd.fish.mvvmtest.ui.DetailActivity
import zyxd.fish.mvvmtest.ui.LazyActivity
import zyxd.fish.mvvmtest.ui.LinkageActivity
import zyxd.fish.mvvmtest.ui.NewsActivity
import zyxd.fish.mvvmtest.ui.fragment.LinkageFragment

class MainActivity : BaseNoModelActivity<ActivityMainBinding>() {

    override fun onCreate(): Int = R.layout.activity_main

    override fun initView() {

    }

    override fun initData() {
        dataBinding.model = this
    }

    public fun item(view: View,position : Int) {
        when(position){
            0 ->{
                startActivity(Intent(this,NewsActivity::class.java))
            }
            1 ->{
                startActivity(Intent(this,DetailActivity::class.java))
            }
            2 ->{
                startActivity(Intent(this,LazyActivity::class.java))
            }
            3 ->{
                startActivity(Intent(this,LinkageActivity::class.java))
            }
        }
    }
}
