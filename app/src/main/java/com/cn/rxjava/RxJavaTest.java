package com.cn.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cn.github.R;
import com.orhanobut.logger.Logger;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ld on 2018/1/27.
 *
 * @author ld
 * @date 2018/1/27
 */

public class RxJavaTest extends AppCompatActivity {
    private final String TAG = "rx JavaTest--";
    CompositeDisposable disposable = new CompositeDisposable();
    Disposable dis;
    int i = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable.just(1,1,2,3,1,3)
                .distinct()
                .subscribe(new Consumer <Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Logger.d("distinct"+integer);
                    }
                });

        Observable.create(new ObservableOnSubscribe <Integer>() {
            @Override
            public void subscribe(ObservableEmitter <Integer> e) throws Exception {
                Logger.d("Observable" + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(3);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).subscribe(new Observer <Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                dis = d;
                disposable.add(d);
            }

            @Override
            public void onNext(Integer value) {
                Logger.d(value);
                if(value == 3){
                    disposable.dispose();
//                    disposable.clear();
                }
                Logger.d("isDisposable --- Next" + disposable.isDisposed()+"\n"+dis.isDisposed());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Logger.d("isDisposable --- Complete" + disposable.isDisposed()+"\n"+dis.isDisposed());
            }
        });
        /*1 创建被观察者  上游--发送事件*/
        Observable observable = Observable.create(new ObservableOnSubscribe <String>() {
            @Override
            public void subscribe(ObservableEmitter <String> e) throws Exception {
                e.onNext("我是上游事件 1 ");
                e.onComplete();
            }
        });
        /*2 创建观察者    下游-- 处理事件*/
        Observer <String> observer = new Observer <String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                System.out.print(TAG + "我是下游" + value);
                Logger.d(TAG + "下游--" + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.print(TAG + "Error" + e.toString());
                Logger.d(TAG + "error --" + e.toString());
            }

            @Override
            public void onComplete() {
                System.out.print(TAG + "Complete");
                Logger.d(TAG + "Complete");
            }
        };
        /*3 订阅*/
//        observable.subscribe(observer);
    }
}
