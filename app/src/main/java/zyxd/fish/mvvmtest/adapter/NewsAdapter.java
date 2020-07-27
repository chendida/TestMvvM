package zyxd.fish.mvvmtest.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import zyxd.fish.lib_common.adapter.BaseDBRVAdapter;
import zyxd.fish.mvvmtest.BR;
import zyxd.fish.mvvmtest.R;
import zyxd.fish.mvvmtest.bean.NewsBean;
import zyxd.fish.mvvmtest.databinding.ItemNewsBinding;

public class NewsAdapter extends BaseDBRVAdapter<NewsBean.StoriesBean, ItemNewsBinding> {
    public NewsAdapter(){
        super(R.layout.item_news, BR.bean);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
