package network;

import io.reactivex.Observable;
import model.homepage.Article;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Request {

    public static final String BASE_URL = "https://www.wanandroid.com/";

    @GET("article/list/{page}/json")
    Observable<ResposeBody<Article>> getHomeArticle(@Path("page") int id);
}
