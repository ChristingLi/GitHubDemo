package com.cn.github;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cn.HorizontalScroll.SlidingActivity;
import com.cn.Utils.HttpUtils;
import com.cn.Utils.ToastUtil;
import com.cn.ViewDarg.DragViewHelperActivity;
import com.cn.ViewDarg.DragViewLayout;
import com.cn.app.MyApplication;
import com.cn.base.LoginLogic;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.https.HttpsUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity";
    private TextView tvTest;
    private Button btnTest;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        initEvent();
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

