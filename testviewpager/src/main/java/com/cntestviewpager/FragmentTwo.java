package com.cntestviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

/**
 * Created by ld on 2017/11/3.
 *
 * @author ld
 * @date 2017/11/3
 */

public class FragmentTwo extends BaseFragment{
    private static final String TAG = "FragmentTwo";
    @Override
    public void onStart() {
        super.onStart();
        Logger.d(TAG+"-onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.d(TAG+"-onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d(TAG+"-onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.d(TAG+"-onStop");
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.d(TAG+"-onViewCreated");
    }
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.d(TAG+"-onCreateView");
        view = inflater.inflate(R.layout.fragment_two,null,false);
        return view;
    }
}
