package com.cn.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.github.R;
import com.orhanobut.logger.Logger;

/**
 * Created by ld on 2017/10/17.
 * @author ld
 */

public class PerFragment extends Fragment{
    private static final String TAG = "PerFragment";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.w(TAG+"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.w(TAG+"onCreate");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_per,container,false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Logger.w(TAG+"onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logger.w(TAG+"onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Logger.w(TAG+"onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Logger.w(TAG+"onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Logger.w(TAG+"onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Logger.w(TAG+"onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Logger.w(TAG+"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Logger.w(TAG+"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Logger.w(TAG+"onDetach");
        super.onDetach();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        Logger.w(TAG+"onAttachFragment");
        super.onAttachFragment(childFragment);
    }
}
