package com.cn.alphatab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.utils.ToastUtil;

/**
 * Created by ld on 2017/10/17.
 */

public class TextFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";
    private String mTitle = "DefaultValue";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTitle = arguments.getString(BUNDLE_TITLE);
        }

        TextView tv = new TextView(getActivity());
        tv.setText(mTitle);
        tv.setTextSize(30);
        tv.setPadding(50, 50, 50, 50);
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), SencondActivity.class));
                ToastUtil.showShortToast(mTitle);
            }
        });

        return tv;
    }

    public static TextFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        TextFragment fragment = new TextFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
