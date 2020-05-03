package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.roclying.wanandroid.activity.ImageActivity;
import com.roclying.wanandroid.activity.MainActivity;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

import model.homepage.Banner;

public class HeaderBannerAdapter extends BannerAdapter<Banner, HeaderBannerAdapter.BannerViewHoder> {

    private Context context;

    public HeaderBannerAdapter(Context context, List<Banner> datas) {
        super(datas);
        this.context = context;

    }

    @Override
    public BannerViewHoder onCreateHolder(ViewGroup parent, int viewType) {

        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHoder(imageView);
    }

    @Override
    public void onBindView(BannerViewHoder holder, Banner data, int position, int size) {
        holder.setImageUrl(data.imagePath);
    }

    public class BannerViewHoder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public BannerViewHoder(@NonNull ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }

        public void setImageUrl(String url) {
            Glide.with(context).load(url).into(imageView);
        }
    }
}
