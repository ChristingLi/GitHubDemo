package com.cn.shopping.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cn.github.R;
import com.cn.shopping.ShoppingActivity;
import com.cn.shopping.bean.ItemBean;

import java.util.List;


public class GoodsDetailAdapter extends BaseAdapter {
    private List<ItemBean> list;
    private Activity activity;
    public GoodsDetailAdapter(ShoppingActivity activity, List<ItemBean> list2) {
        this.activity=activity;
        this.list=list2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder;
        if (convertView==null){
            convertView= LayoutInflater.from(activity).inflate(R.layout.activity_goods_detail_item,null);
            viewholder=new ViewHolder();
            viewholder.mTvName =  convertView.findViewById(R.id.tv_name);
            viewholder.mTvPrice =  convertView.findViewById(R.id.tv_price);

            convertView.setTag(viewholder);
        }else {
            viewholder = (ViewHolder) convertView.getTag();

        }
        viewholder.mTvName.setText(list.get(position).getValue()+"*"+list.get(position).getNote2());
        viewholder.mTvPrice.setText("￥"+list.get(position).getNote1());
        return convertView;
    }
    class ViewHolder {
        TextView mTvName, mTvPrice;

    }

}
