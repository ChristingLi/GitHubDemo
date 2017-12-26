package com.cn.shopping.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cn.github.R;
import com.cn.shopping.ShoppingActivity;
import com.cn.shopping.bean.GoodsBean;

import java.util.List;

/**
 * Created by ld on 2017/11/9.
 *
 * @author ld
 * @date 2017/11/9
 */

public class GoodsAdapter extends BaseAdapter{
    private List<GoodsBean> list;
    private Context mContext;
    private CatograyAdapter catogaryAdapter;

    public GoodsAdapter(Context context,List<GoodsBean> bean,CatograyAdapter adapter){
        this.mContext = context;
        this.list = bean;
        this.catogaryAdapter = adapter;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewholder;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.shopcart_right_listview,null);
            viewholder=new ViewHolder();
            viewholder.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewholder.mTvOriginPrice = (TextView) convertView.findViewById(R.id.tv_original_price);
            viewholder.mTvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            viewholder.mImgAdd = (ImageView) convertView.findViewById(R.id.iv_add);
            viewholder.mImgRemove = (ImageView) convertView.findViewById(R.id.iv_remove);
            viewholder.mTvAccount = (TextView) convertView.findViewById(R.id.tv_acount);
            viewholder.mImgPic = (ImageView) convertView.findViewById(R.id.iv_pic);
            viewholder.mRelaItem = (RelativeLayout) convertView.findViewById(R.id.rl_item);
            convertView.setTag(viewholder);
        }else {
            viewholder = (ViewHolder) convertView.getTag();

        }
        viewholder.mTvName.setText(list.get(position).getTitle());
        viewholder.mTvOriginPrice.setText("￥"+list.get(position).getOriginal_price());
        viewholder.mTvOriginPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
        viewholder.mTvPrice.setText("￥"+list.get(position).getPrice());


        if(list.get(position)!=null){
            //默认进来数量
            if (list.get(position).getNum()<1){
                viewholder.mTvAccount.setVisibility(View.INVISIBLE);
                viewholder.mImgRemove.setVisibility(View.INVISIBLE);
                catogaryAdapter.notifyDataSetChanged();
            }else{
                viewholder.mTvAccount.setVisibility(View.VISIBLE);
                viewholder.mImgRemove.setVisibility(View.VISIBLE);
                viewholder.mTvAccount.setText(String.valueOf(list.get(position).getNum()));
                catogaryAdapter.notifyDataSetChanged();
            }
        }else{
            viewholder.mTvAccount.setVisibility(View.INVISIBLE);
            viewholder.mImgRemove.setVisibility(View.INVISIBLE);
        }

        //商品图片
        if(list.get(position).getIcon()!=null){
            Glide.with(mContext).load(list.get(position).getIcon()).into(viewholder.mImgPic);
//            ImageLoader.getInstance().displayImage(
//                    list.get(position).getIcon(), viewholder.mImgPic);
        }


        viewholder.mImgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ((ShoppingActivity)mContext).getSelectedItemCountById(list.get(position).getProduct_id());
                Log.i("fyg","mImgAdd"+String.valueOf(count));
                if (count < 1) {
                    viewholder.mImgRemove.setAnimation(getShowAnimation());
                    viewholder.mImgRemove.setVisibility(View.VISIBLE);
                    viewholder.mTvAccount.setVisibility(View.VISIBLE);
                }

                ((ShoppingActivity)mContext).handlerCarNum(1,list.get(position),true);
                catogaryAdapter.notifyDataSetChanged();

                int[] loc = new int[2];
                viewholder.mImgAdd.getLocationInWindow(loc);
                for (int i=0;i<loc.length;i++)
                {
                    Log.i("fyg",String.valueOf(loc[i]));
                }
                int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                ImageView ball = new ImageView(mContext);
                ball.setImageResource(R.mipmap.number);
                ((ShoppingActivity)mContext).setAnim(ball, startLocation);// 开始执行动画

            }
        });

        viewholder.mImgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ((ShoppingActivity)mContext).getSelectedItemCountById(list.get(position).getProduct_id());
                Log.i("fyg","mImgRemove"+String.valueOf(count));
                if (count < 2) {
                    viewholder.mImgRemove.setAnimation(getHiddenAnimation());
                    viewholder.mImgRemove.setVisibility(View.GONE);
                    viewholder.mTvAccount.setVisibility(View.GONE);
                }
                ((ShoppingActivity)mContext).handlerCarNum(0,list.get(position),true);
                catogaryAdapter.notifyDataSetChanged();

            }
        });

        viewholder.mRelaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(list.get(position).getPackage_product_info()!=null&&list.get(position).getTitle()!=null){
//                    catograyAdapter.notifyDataSetChanged();
//                  ((MainActivity)context).showDetailSheet(list.get(position).getPackage_product_info(),list.get(position).getTitle());
//                }else{
////                    Toast.makeText(context, "没有详情!", Toast.LENGTH_SHORT).show();
//                }
            }
        });
        return convertView;
    }
    class ViewHolder{
        TextView mTvName, mTvOriginPrice, mTvPrice;
        ImageView mImgAdd, mImgRemove, mImgPic;
        TextView mTvAccount;
        RelativeLayout mRelaItem;
    }
    //显示减号的动画
    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
    //隐藏减号的动画
    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
}
