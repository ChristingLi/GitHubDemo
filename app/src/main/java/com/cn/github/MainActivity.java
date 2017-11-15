package com.cn.github;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.cn.alphatab.AlphaActivity;
import com.cn.bean.ImageBean;
import com.cn.bean.JsonEntity;
import com.cn.customview.AutoLine;
import com.cn.customview.Keyboard;
import com.cn.customview.SwitchButton;
import com.cn.customview.SwitchButtons;
import com.cn.dbfile.GreenDaoEntity;
import com.cn.dbfile.GreenDaoEntityDao;
import com.cn.horizontalScroll.SlidingActivity;
import com.cn.listener.MyDrawerLayoutListener;
import com.cn.project.HomeActivity;
import com.cn.project.PlanTwoActivity;
import com.cn.shopping.ShoppingActivity;
import com.cn.train.FarstJsonTrain;
import com.cn.utils.GreenDaoManager;
import com.cn.utils.HttpUtils;
import com.cn.utils.ToastUtil;
import com.cn.viewdarg.DragMenuActivity;
import com.cn.viewdarg.DragViewHelperActivity;
import com.cn.viewdarg.HorizontalDragActivity;
import com.cn.application.MyApplication;
import com.cn.base.LoginLogic;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity";
    private TextView tvTest;
    private Button btnTest;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    String json = "{\"name\":\"chenggang\",\"age\":24}";
    String arrayAyy = "[[\'马云',50],null,[\'马化腾',30]]";
    private GreenDaoEntityDao dao;
    private ImmersionBar mImmersionBar;
    /*butter knife*/
    @BindView(R.id.btn_entity)
    Button mbtEntity;
    @BindViews({R.id.btn_list,R.id.btn_byte,R.id.btn_complex,R.id.btn_json,R.id.btn_green_insert,R.id.btn_green_read})
    List<Button> buttons;
    @BindView(R.id.switch_buttons)
    SwitchButtons mSwitchButton;
    @BindView(R.id.auto_line)
    AutoLine mAutoLine;
    @BindView(R.id.ll_switch_button)
    LinearLayout mSwitchll;
    @BindView(R.id.test)
    Button mTestBtn;
    @BindView(R.id.btn_shop_cart)
    Button mBtShopCart;
    @BindView(R.id.btn_zx)
    Button mBtZx;
    public static final int REQUEST_CODE = 111;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSwitchButton.setChecked(true);
        mSwitchButton.toggle(false);
        mSwitchButton.setEnabled(false);
        switchClick();
        initAuto();
        initImmerSionBar();
        initViews();
        initEvent();
        dao = GreenDaoManager.getInstance().getGreedDaoSession().getGreenDaoEntityDao();
        mTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HorTab.class);
                startActivity(intent);
            }
        });
    }

    private void switchClick() {
        mSwitchll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mSwitchButton.isEnabled()){

                }else {
                    ToastUtil.showShortToast("处于不可点击事件");
                }
            }
        });
        mSwitchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mSwitchButton.setOnCheckedChangeListener(new SwitchButtons.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButtons view, boolean isChecked) {
                ToastUtil.showShortToast("123");
            }
        });
    }
    private void initAuto() {
        for(int i=0; i < 10;i++){
            View item = LayoutInflater.from(this).inflate(R.layout.button_sample,null);
            Button itemBtn = item.findViewById(R.id.btn_sample);
            String text = "测试数据测试测试蹲厕所洒大地上大树大根深测试数据测试测试蹲厕所洒大地上大树大根深测试数据测试测试蹲厕所" +
                    "洒大地上大树大根深测试数据测试测试蹲厕所洒大地上大树大根深测试数据测试测试蹲厕所洒大地上大树大根深测试数据测试测试蹲厕所洒大地上大树大根深";
            itemBtn.setText(text.substring(0,i+1));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(2,0,2,0);
            item.setLayoutParams(params);
            mAutoLine.addView(item);
        }
    }
    @OnClick(R.id.btn_zx)
    public void zxClick(){
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }
    @OnClick(R.id.btn_shop_cart)
    public void shopCart(View view){
        Intent intent = new Intent(this, ShoppingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_green_insert)
    public void greedDaoInsert(){
        GreenDaoEntity entity = new GreenDaoEntity(null,"GreenDao");
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
//        mDrawerLayout.addDrawerListener(new MyDrawerLayoutListener(mImmersionBar));
        mToolbar = findViewById(R.id.toolbar);
        tvTest = findViewById(R.id.test_text);
        btnTest = findViewById(R.id.test_btn);
        mNavigationView = findViewById(R.id.design_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(MainActivity.this);
        mbtEntity = findViewById(R.id.btn_entity);
        initToolbar();
    }
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        setTitle("");
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toogle);
        toogle.syncState();
    }
    class DrawLayoutListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
        }
        @Override
        public void onDrawerOpened(View drawerView) {
        }
        @Override
        public void onDrawerClosed(View drawerView) {
        }
        @Override
        public void onDrawerStateChanged(int newState) {
        }
    }
    @SuppressLint("NewApi")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_1){
            ToastUtil.showShortToastCenter("Home Page");
            Intent mIntent = new Intent(MainActivity.this, SlidingActivity.class);
            startActivity(mIntent);
        }else if(id == R.id.nav_2){
            ToastUtil.showShortToastCenter("DragMenuView");
            Intent mIntent = new Intent(MainActivity.this, DragMenuActivity.class);
            startActivity(mIntent);
        }else if(id == R.id.nav_3){
            ToastUtil.showShortToastCenter("Mine");
            Intent mIntent = new Intent(MainActivity.this, HorizontalDragActivity.class);
            startActivity(mIntent);
        }else if(id == R.id.nav_4){
            ToastUtil.showShortToastCenter("Praise");
            Intent mIntent = new Intent(MainActivity.this, DragViewHelperActivity.class);
            startActivity(mIntent);
        }else if(id == R.id.nav_5){
            ToastUtil.showShortToast("即将进入");
            Intent mIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(mIntent);
        }else if(id == R.id.nav_6){
            Intent mIntent = new Intent(MainActivity.this, PlanTwoActivity.class);
            startActivity(mIntent);
        }
        else if(id == R.id.nav_7){
            Intent mIntent = new Intent(MainActivity.this, AlphaActivity.class);
            startActivity(mIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }else if(id == R.id.nav_8){
            Intent mIntent = new Intent(MainActivity.this, TestFragmentActivity.class);
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
//                            JSONObject responseObj = new JSONObject(response);
//                            tvTest.setText(responseObj.toString());
                            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(response);
                            JSONArray array = jsonObject.getJSONArray("美女");
                            List<ImageBean> data = JSON.parseArray(array.toJSONString(), ImageBean.class);
                            Logger.d(data);
                        } else {
                            ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_null_exception));
                        }
//                    } catch (JSONException e) {
//                        ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_json_exception));
                    }catch (Exception e) {
                        ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_json_exception));
                    } finally {
                    }
                    break;
                case 101://https
                    break;
                default:
            }
        }
        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }
    private void initImmerSionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.transparentStatusBar()  //透明状态栏，不写默认透明色
//                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
//                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
                .statusBarColor(R.color.orange)     //状态栏颜色，不写默认透明色
//               .navigationBarColor(R.color.orange) //导航栏颜色，不写默认黑色
//              .barColor(R.color.orange)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
                .statusBarAlpha(0.0f)  //状态栏透明度，不写默认0.0f
//                .navigationBarAlpha(0.4f)  //导航栏透明度，不写默认0.0F
//                .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认0.0f
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
//                .flymeOSStatusBarFontColor(R.color.btn3)  //修改flyme OS状态栏字体颜色
//                .fullScreen(true)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//                .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
//                .addViewSupportTransformColor(mToolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//                .titleBar(view)    //解决状态栏和布局重叠问题，任选其一
//                .titleBarMarginTop(mDrawerLayout)     //解决状态栏和布局重叠问题，任选其一
//                .statusBarView(view)  //解决状态栏和布局重叠问题，任选其一
                .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
//                .supportActionBar(true) //支持ActionBar使用
//                .statusBarColorTransform(R.color.orange)  //状态栏变色后的颜色
//                .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
//                .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
//                .removeSupportView(mToolbar)  //移除指定view支持
//                .removeSupportAllView() //移除全部view支持
                .navigationBarEnable(true)   //是否可以修改导航栏颜色，默认为true
//                .navigationBarWithKitkatEnable(true)  //是否可以修改安卓4.4和emui3.1手机导航栏颜色，默认为true
//                .fixMarginAtBottom(true)   //已过时，当xml里使用android:fitsSystemWindows="true"属性时,解决4.4和emui3.1手机底部有时会出现多余空白的问题，默认为false，非必须
//                .addTag("tag")  //给以上设置的参数打标记
//                .getTag("tag")  //根据tag获得沉浸式参数
//                .reset()  //重置所以沉浸式参数
//                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
//                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
//                .setOnKeyboardListener(new OnKeyboardListener() {    //软键盘监听回调
//                    @Override
//                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
//                        Logger.d(isPopup);  //isPopup为true，软键盘弹出，为false，软键盘关闭
//                    }
//                })
                .init();  //必须调用方可沉浸式
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }
}

