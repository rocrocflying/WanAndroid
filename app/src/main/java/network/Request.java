package network;

import io.reactivex.Observable;
import model.homepage.Article;
import model.homepage.Meizhi;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Request {

    public static final String BASE_URL = "https://www.wanandroid.com/";
    public static final String GANKIO_URL = "https://gank.io/";

    @GET("article/list/{page}/json")
    Observable<ResposeBody<Article>> getHomeArticle(@Path("page") int page);

    @GET("api/v2/data/category/Girl/type/Girl/page/{page}/count/20")
    Observable<MeizhiRequestBody> getMeizhiList(@Path("page") int page);
}
