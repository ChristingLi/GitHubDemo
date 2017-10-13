package com.cn.github;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.cn.Bean.JsonEntity;
import com.cn.DbFile.GreenDaoEntity;
import com.cn.DbFile.GreenDaoEntityDao;
import com.cn.HorizontalScroll.SlidingActivity;
import com.cn.Train.FarstJsonTrain;
import com.cn.Utils.GreenDaoManager;
import com.cn.Utils.HttpUtils;
import com.cn.Utils.ToastUtil;
import com.cn.ViewDarg.DragViewHelperActivity;
import com.cn.ViewDarg.HorizontalDragActivity;
import com.cn.Application.MyApplication;
import com.cn.base.LoginLogic;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity";
    private TextView tvTest;
    private Button btnTest;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    String json = "{\"name\":\"chenggang\",\"age\":24}";
    String arrayAyy = "[[\'马云',50],null,[\'马化腾',30]]";
    private GreenDaoEntityDao dao;
    /*butter knife*/
    @BindView(R.id.btn_entity)
    Button mbtEntity;
    @BindViews({R.id.btn_list,R.id.btn_byte,R.id.btn_complex,R.id.btn_json,R.id.btn_green_insert,R.id.btn_green_read})
    List<Button> buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        dao = GreenDaoManager.getInstance().getGreedDaoSession().getGreenDaoEntityDao();
        initViews();
        initEvent();
    }
    @OnClick(R.id.btn_green_insert)
    public void greedDaoInsert(){

        GreenDaoEntity entity = new GreenDaoEntity(null,"GreenDao");
//        entity.setId(null);null
//        entity.setName("GreenDao");
        dao.insert(entity);
    }
    @OnClick(R.id.btn_green_read)
    public void greedDaoRead(View view){
        List<GreenDaoEntity> list = dao.queryBuilder()
                .where(GreenDaoEntityDao.Properties.Id.notEq(999))
                .limit(5)
                .build().list();
        Logger.d(list.size());
        Logger.d(list.get(0).getName()+"--"+list.get(1).getName());

    }
    @OnClick(R.id.btn_entity)
    public void entity(View view){
        String name = "toJsonNmae";
        FarstJsonTrain.entity(name);
    }
    @OnClick(R.id.btn_list)
    public void list(View view){
        FarstJsonTrain.list();
    }
    @OnClick(R.id.btn_byte)
    public void bytes(View view){
        FarstJsonTrain.bytes(arrayAyy);
    }
    @OnClick(R.id.btn_complex)
    public void comples(View view){
        FarstJsonTrain.comlpex();
    }
    @OnClick(R.id.btn_json)
    public void json(View view){
        JsonEntity entity = JSON.parseObject(json,JsonEntity.class);
        Logger.d("姓名是："+entity.getName()+",年龄是："+entity.getAge());
    }
    private void Logger() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d(TAG);
        Logger.clearLogAdapters();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(0)
                .methodOffset(3)
                .tag("tag")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        Logger.addLogAdapter(new DiskLogAdapter());
        Logger.w("no thread info and only 1 method");

        Logger.clearLogAdapters();
        formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.i("no thread info and method info");

        Logger.t("tag").e("Custom tag for only one use");

        Logger.json("{ \"key\": 3, \"value\": something}");

        Logger.d(Arrays.asList("foo", "bar"));

        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key1", "value2");

        Logger.d(map);

        Logger.clearLogAdapters();
        formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .tag("MyTag")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        Logger.w("my log message with my tag");
    }

    private void initEvent(){
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJson();
            }
        });
    }
    private void getJson(){
        if(HttpUtils.isNetWorkAvailable(this)){
            try {
                LoginLogic.Instance(MainActivity.this).getJson(new GetJsonStringCallback());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.network_enable));
        }
    }
    private void initViews(){
        mDrawerLayout = findViewById(R.id.drawlayout);
        mToolbar = findViewById(R.id.toolbar);
        tvTest = findViewById(R.id.test_text);
        btnTest = findViewById(R.id.test_btn);
        mNavigationView = findViewById(R.id.design_navigation_view);
        mbtEntity = findViewById(R.id.btn_entity);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        setTitle("");
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        mNavigationView.setNavigationItemSelectedListener(MainActivity.this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_1){
            ToastUtil.showShortToastCenter("Home Page");
            Intent mIntent = new Intent(MainActivity.this, SlidingActivity.class);
            startActivity(mIntent);
        }else if(id == R.id.nav_2){
            ToastUtil.showShortToastCenter("Picture");
        }else if(id == R.id.nav_3){
            ToastUtil.showShortToastCenter("Mine");
            Intent mIntent = new Intent(MainActivity.this, HorizontalDragActivity.class);
            startActivity(mIntent);
        }else if(id == R.id.nav_4){
            ToastUtil.showShortToastCenter("Praise");
            Intent mIntent = new Intent(MainActivity.this, DragViewHelperActivity.class);
            startActivity(mIntent);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * get接口的自定义回调函数*/
    public class GetJsonStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {//showProgressDialog("");//显示进度加载框
        }

        @Override
        public void onAfter(int id) {//dismissProgressDialog();//隐藏进度加载框
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_again));
            Log.w(TAG,"{onError}e="+e.toString());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：response="+response);
            switch (id) {
                case 100://http
                    try {
                        if (response != null && !"".equals(response)){
                            //解析
                            JSONObject responseObj = new JSONObject(response);
                            tvTest.setText(responseObj.toString());
                        }
                        else {
                            ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_null_exception));
                        }
                    } catch (JSONException e) {
                        ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_json_exception));
                    }catch (Exception e) {
                        ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_json_exception));
                    } finally {
                    }
                    break;
                case 101://https
                    break;
            }
        }
        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }
}

