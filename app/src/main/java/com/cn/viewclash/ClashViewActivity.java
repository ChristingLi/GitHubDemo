package com.cn.viewclash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cn.github.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ld on 2018/4/10.
 *
 * @author ld
 * @date 2018/4/10
 * 描    述：
 */
public class ClashViewActivity extends AppCompatActivity {
    @BindView(R.id.list_clash)
    ListView listClash;

    String[] str = {"1","2","d","1","2","d","1","2","d","1","2","d","1","2","d"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clash);
        ButterKnife.bind(this);
        listClash.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,str));
    }
}
