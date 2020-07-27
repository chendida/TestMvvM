package zyxd.fish.mvvmtest.ui

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import zyxd.fish.lib_common.base.BaseNoModelActivity
import zyxd.fish.mvvmtest.R
import zyxd.fish.mvvmtest.databinding.ActivityLazyBinding
import zyxd.fish.mvvmtest.ui.fragment.LazyFragment

class LazyActivity : BaseNoModelActivity<ActivityLazyBinding>() {

    private val mFragments = ArrayList<Fragment>()
    private val mTitles = mutableListOf<String>("Android", "iOS", "人工智能", "代码人生")

    override fun onCreate() = R.layout.activity_lazy

    override fun initView() {
        title = "Fragment懒加载使用示例"
        val viewPager = dataBinding.viewPager
        val tabLayout = dataBinding.tabLayout

        mFragments.add(LazyFragment.getInstance("5562b419e4b00c57d9b94ae2"))
        mFragments.add(LazyFragment.getInstance("5562b405e4b00c57d9b94a41"))
        mFragments.add(LazyFragment.getInstance("57be7c18128fe1005fa902de"))
        mFragments.add(LazyFragment.getInstance("5c9c7cca1b117f3c60fee548"))

        //保存fragment的状态
        viewPager.offscreenPageLimit = mFragments.size
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[0]), 0)
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[1]), 1)
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[2]), 2)
        tabLayout.addTab(tabLayout.newTab().setText(mTitles[3]), 3)

        val myFragmentPagerAdapter = MyFragmentPagerAdapter(this)
        viewPager.adapter = myFragmentPagerAdapter
        TabLayoutMediator(tabLayout,viewPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = mTitles[position]
            }).attach()
    }

    override fun initData() {
    }

    inner class MyFragmentPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
        override fun getItemCount(): Int {
            return mFragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return mFragments[position]
        }
    }
}
