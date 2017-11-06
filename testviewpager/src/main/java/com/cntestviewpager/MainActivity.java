package com.cntestviewpager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewpager)
    ViewPager pager;
    @BindView(R.id.test1)
    Button bt1;
    @BindView(R.id.test2)
    Button bt2;
    @BindView(R.id.test3)
    Button bt3;
    @BindView(R.id.test4)
    Button bt4;
    @BindView(R.id.btn_intent)
    Button btIntent;

    private MyAdapter adapter;
    private FragmentOne one;
    private FragmentTwo two;
    private FragmentThree three;
    private FragmentFour four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



//        adapter = new MyAdapter(getSupportFragmentManager());
//        one = new FragmentOne();
//        two = new FragmentTwo();
//        three = new FragmentThree();
//        four = new FragmentFour();
//        adapter.setFragments(one);
//        adapter.setFragments(two);
//        adapter.setFragments(three);
//        adapter.setFragments(four);
//        pager.setOffscreenPageLimit(1);
//        pager.setAdapter(adapter);

    }
    @OnClick(R.id.btn_intent)
    public void intentBt(View view){
        startActivity(new Intent(MainActivity.this,SendMesActivity.class));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event){
        Log.d("Main","ReceiveMes");
        Toast.makeText(this,"get mes",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    class MyAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener{
        List<Fragment> fragments = new ArrayList<>();

        public void setFragments(Fragment fragment){
            fragments.add(fragment);
        }

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (position == 0)
                removeFragment(container,position);
            return super.instantiateItem(container, position);
        }
        private void removeFragment(ViewGroup container,int index) {
            String tag = getFragmentTag(container.getId(), index);
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            if (fragment == null)
                return;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(fragment);
            ft.commit();
            ft = null;
            getSupportFragmentManager().executePendingTransactions();
        }
        private String getFragmentTag(int viewId, int index) {
            try {
                Class<FragmentPagerAdapter> cls = FragmentPagerAdapter.class;
                Class<?>[] parameterTypes = { int.class, long.class };
                Method method = cls.getDeclaredMethod("makeFragmentName",
                        parameterTypes);
                method.setAccessible(true);
                String tag = (String) method.invoke(this, viewId, index);
                return tag;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @OnClick(R.id.test1)
    public void click1(){
        pager.setCurrentItem(0);
    }
    @OnClick(R.id.test2)
    public void click2(){
        pager.setCurrentItem(1);
    }
    @OnClick(R.id.test3)
    public void click3(){
        pager.setCurrentItem(2);
    }
    @OnClick(R.id.test4)
    public void click4(){
        pager.setCurrentItem(3);
    }
}
