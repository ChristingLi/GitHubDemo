package com.cn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cn.bean.ImageBean;
import com.cn.github.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ld on 2017/11/7.
 *
 * @author ld
 * @date 2017/11/7
 */

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.ViewHolder>{
    private Context mContext;
    private List<ImageBean> mData = new ArrayList<>();

    public WaterFallAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_water_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        ImageBean bean = mData.get(position);
        String pixel = bean.getPixel();
        int x = mContext.getResources().getDisplayMetrics().widthPixels - 32;
        String wid = pixel.substring(0,pixel.indexOf("*"));
        String he = pixel.substring(pixel.indexOf("*")+1,pixel.length());
        int length = Integer.parseInt(he);
        params.height = length;
        holder.itemView.setLayoutParams(params);
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .override(x,length) /*主动设置View的大小 图片大小问题➡️RecyclerView复用机制*/
                .placeholder(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(false);
        Glide.with(mContext).load(bean.getImgsrc()).apply(options)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_image_view)
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public List<ImageBean> getList(){
        return mData;
    }
}
