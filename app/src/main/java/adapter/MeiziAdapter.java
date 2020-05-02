package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.roclying.wanandroid.R;
import com.roclying.wanandroid.activity.ImageActivity;

import java.util.ArrayList;

import model.homepage.Meizhi;

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.MeizhiHolder> {

    private ArrayList<Meizhi> meizhiList;
    public Context context;


    public MeiziAdapter(ArrayList<Meizhi> meizhiList, Context context) {
        this.meizhiList = meizhiList;
        this.context = context;
    }

    @NonNull
    @Override
    public MeizhiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_meizhi_layout, null);
        return new MeizhiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeizhiHolder holder, int position) {

        holder.loadData(meizhiList.get(position));
    }

    @Override
    public int getItemCount() {
        return meizhiList.size();
    }

    public class MeizhiHolder extends RecyclerView.ViewHolder {

        private ImageView meizhiImg;

        public MeizhiHolder(@NonNull View itemView) {
            super(itemView);
            meizhiImg = itemView.findViewById(R.id.iv_meizhi);
        }

        public void loadData(final Meizhi meizhi) {

            Glide.with(context).load(meizhi.url).into(meizhiImg);
            meizhiImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ImageActivity.class);
                    intent.putExtra("url", meizhi.url);
                    context.startActivity(intent);
                }
            });
        }
    }
}
