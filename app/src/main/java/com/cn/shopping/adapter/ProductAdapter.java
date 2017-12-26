package com.cn.shopping.adapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.github.R;
import com.cn.shopping.ShoppingActivity;
import com.cn.shopping.bean.GoodsBean;
import com.cn.shopping.util.StringUtils;


/***
 * 底部购物车
 */
public class ProductAdapter extends BaseAdapter {
    GoodsAdapter goodsAdapter;
    private ShoppingActivity activity;
    private SparseArray<GoodsBean> dataList;
    public ProductAdapter(ShoppingActivity activity, GoodsAdapter goodsAdapter, SparseArray<GoodsBean> dataList) {
        this.goodsAdapter =goodsAdapter;
        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.valueAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewholder;
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.product_item, null);
            viewholder = new ViewHolder();
            viewholder.mTvName =  view.findViewById(R.id.tv_name);
            viewholder.mTvPrice =  view.findViewById(R.id.tv_price);
            viewholder.mImgAdd =  view.findViewById(R.id.iv_add);
            viewholder.mIngRemove =  view.findViewById(R.id.iv_remove);
            viewholder.mTvCount =  view.findViewById(R.id.tv_count);

            view.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) view.getTag();
        }


            StringUtils.filtNull(viewholder.mTvName,dataList.valueAt(position).getTitle());//商品名称
            StringUtils.filtNull(viewholder.mTvPrice,"￥"+dataList.valueAt(position).getPrice());//商品价格
            viewholder.mTvCount.setText(String.valueOf(dataList.valueAt(position).getNum()));//商品数量

            viewholder.mImgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.handlerCarNum(1,dataList.valueAt(position),true);
                    goodsAdapter.notifyDataSetChanged();

                }
            });
            viewholder.mIngRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.handlerCarNum(0,dataList.valueAt(position),true);
                    goodsAdapter.notifyDataSetChanged();
                }
            });

        return view;
    }

    class ViewHolder {
        TextView mTvPrice;
        TextView mTvName;
        ImageView mImgAdd, mIngRemove;
        TextView mTvCount;
    }

}