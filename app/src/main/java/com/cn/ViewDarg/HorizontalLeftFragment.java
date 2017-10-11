package com.cn.ViewDarg;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cn.Adapter.HorizontalLeftMenuAdapter;
import com.cn.Model.MenuItem;
import com.cn.github.R;

/**
 * Created by ld on 2017/10/10.
 */

public class HorizontalLeftFragment extends ListFragment{
    private static final String TAG = "HorizontalLeftFragment";
    private static final int SIZE_MENU_ITEM = 3;
    private MenuItem[] items = new MenuItem[SIZE_MENU_ITEM];
    private LayoutInflater mInflater;
    private HorizontalLeftMenuAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(getActivity());
        MenuItem menuItem = null;
        for(int i=0;i < SIZE_MENU_ITEM;i++){
            menuItem = new MenuItem(getResources().getStringArray(R.array.array_left_menu)[i], false,
                    R.drawable.ic_homepage, R.drawable.ic_mine);
            items[i] = menuItem;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(0xffffffff);
        setListAdapter(mAdapter = new HorizontalLeftMenuAdapter(getActivity(),items));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(mMenuItemSelectedListener != null){
            mMenuItemSelectedListener.menuItemSeletced(((MenuItem) getListAdapter().getItem(position)).text);
        }
        mAdapter.setSelected(position);
    }
    public interface OnMenuItemSelectedListener{
        void menuItemSeletced(String title);
    }
    private OnMenuItemSelectedListener mMenuItemSelectedListener;

    public void setOnMenuItemSelectedListener(OnMenuItemSelectedListener menuItemSelectedListener) {
        this.mMenuItemSelectedListener = menuItemSelectedListener;
    }
}
