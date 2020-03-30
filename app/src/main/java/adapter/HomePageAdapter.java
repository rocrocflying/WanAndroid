package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roclying.wanandroid.R;
import com.roclying.wanandroid.activity.WebDetailActivity;

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

        final Article.ArticleItem item = list.get(position);
        holder.titleTv.setText(item.title);
        holder.typeTv.setText(item.shareUser);
        holder.timeTv.setText(item.niceDate);

        holder.setonItemClickListener(new HomepageViewhoder.OnItemClickListener() {
            @Override
            public void onClick() {
                Intent intent = new Intent(context, WebDetailActivity.class);
                intent.putExtra("title", item.title);
                intent.putExtra("link", item.link);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HomepageViewhoder extends RecyclerView.ViewHolder {

        private TextView titleTv;
        private TextView typeTv;
        private TextView timeTv;
        private OnItemClickListener onItemClickListener;

        public HomepageViewhoder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.tv_title);
            typeTv = itemView.findViewById(R.id.tv_type);
            timeTv = itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick();
                }
            });
        }

        public void setonItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        interface OnItemClickListener {

            void onClick();
        }
    }

}
