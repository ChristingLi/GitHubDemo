package com.cn.shopping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cn.github.R;
import com.cn.shopping.bean.CatograyBean;

import java.util.List;

/**
 * Created by ld on 2017/11/9.
 *
 * @author ld
 * @date 2017/11/9
 */

public class CatograyAdapter extends BaseAdapter{
    private Context mContext;
    private List<CatograyBean> list;
    private int selection = 0;

    public CatograyAdapter(Context context,List<CatograyBean> beans){
        this.mContext = context;
        this.list = beans;
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
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        View view = convertview;
        final ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.shopcart_left_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.mTvCatogray = view.findViewById(R.id.tv_catogray);
            viewHolder.mTvCount = view.findViewById(R.id.tv_count);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
            viewHolder.mTvCatogray.setText(list.get(position).getKind());
            int count = 0;
            for (int i = 0; i < list.get(position).getList().size(); i++) {
                count += list.get(position).getList().get(i).getNum();
            }
            list.get(position).setCount(count);

            if (count <= 0) {
                viewHolder.mTvCount.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.mTvCount.setVisibility(View.VISIBLE);
                viewHolder.mTvCount.setText(list.get(position).getCount()+"");
            }
            if (position == selection) {
                viewHolder.mTvCatogray.setBackgroundResource(R.drawable.rec_red_left_stroke);
                viewHolder.mTvCatogray.setTextColor(mContext.getResources().getColor(R.color.black));
            } else {
                viewHolder.mTvCatogray.setBackgroundResource(R.drawable.empty);
                viewHolder.mTvCatogray.setTextColor(mContext.getResources().getColor(R.color.gray));
            }
        return view;
    }
    public void setSelection(int selection){
        this.selection = selection;
    }
    class ViewHolder{
        TextView mTvCatogray;
        TextView mTvCount;
    }
}
