package com.cn.callback;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.cn.github.R;
import com.kingja.loadsir.callback.Callback;

/**
 * Created by ld on 2017/10/19.
 *
 * @author ld
 * @date 2017/10/19
 */

public class CoustomCallback extends Callback{
    @Override
    protected int onCreateView() {
        return R.layout.layout_custom;
    }
    @Override
    protected boolean onRetry(final Context context, View view) {
        Toast.makeText(context.getApplicationContext(), "Hello buddy, how r u! :p", Toast.LENGTH_SHORT).show();
        (view.findViewById(R.id.iv_gift)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "It's your gift! :p", Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }
}
