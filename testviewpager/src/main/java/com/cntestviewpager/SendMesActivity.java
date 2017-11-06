package com.cntestviewpager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ld on 2017/11/6.
 *
 * @author ld
 * @date 2017/11/6
 */

public class SendMesActivity extends AppCompatActivity{
    @BindView(R.id.btn_send)
    Button send;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mes);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.btn_send)
    public void send(View view){
        EventBus.getDefault().post(new MessageEvent("mes"));
    }
}
