package zyxd.fish.mvvmtest.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zyxd.fish.mvvmtest.bean.JueJinBean;
import zyxd.fish.mvvmtest.bean.NewsBean;

public interface ApiService {
    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    String JUE_JIN_BASE_URL = "http://timeline-merger-ms.juejin.im/v1/";

    /**
     * 测试接口
     */
    @GET("news/latest")
    Observable<NewsBean> news();

    /**
     * 掘金接口
     */
    @GET("get_entry_by_timeline")
    Observable<JueJinBean> jueJin(@Query("category") String category, @Query("limit") String limit,
                                  @Query("src") String src);
}
