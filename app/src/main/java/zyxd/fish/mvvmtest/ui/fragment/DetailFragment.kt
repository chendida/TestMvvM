package zyxd.fish.mvvmtest.ui.fragment

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import zyxd.fish.lib_common.base.BaseNoModelFragment
import zyxd.fish.mvvmtest.R
import zyxd.fish.mvvmtest.databinding.FragmentDetailBinding


class DetailFragment : BaseNoModelFragment<FragmentDetailBinding>() {

    companion object{
        fun getInstance(url : String) : DetailFragment{
            val bundle = Bundle()
            bundle.putString("url",url)
            val detailFragment = DetailFragment()
            detailFragment.arguments = bundle
            return detailFragment
        }
    }

    override fun initData() {
        val url = arguments?.get("url") as String
        dataBinding.webView.loadUrl(url)
    }

    override fun initView() {
        dataBinding.webView.settings.javaScriptEnabled = true
        dataBinding.webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }

    override fun onCreate() = R.layout.fragment_detail

}
