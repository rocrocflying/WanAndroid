package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roclying.wanandroid.R;

import java.util.ArrayList;

import model.homepage.Article;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.HomepageViewhoder> {

    private Context context;
    private ArrayList<Article.ArticleItem> list = new ArrayList<>();

    public HomePageAdapter(Context context, ArrayList<Article.ArticleItem> list) {

        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public HomepageViewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_article_layout, null);
        return new HomepageViewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomepageViewhoder holder, int position) {

        Article.ArticleItem item = list.get(position);
        holder.titleTv.setText(item.title);
        holder.typeTv.setText(item.link);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HomepageViewhoder extends RecyclerView.ViewHolder {

        private TextView titleTv;
        private TextView typeTv;

        public HomepageViewhoder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv_title);
            typeTv = itemView.findViewById(R.id.tv_type);
        }
    }

}
