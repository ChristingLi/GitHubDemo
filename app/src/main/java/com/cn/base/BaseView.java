package com.cn.base;

/**
 * Created by ld on 2017/11/15.
 *
 * @author ld
 * @date 2017/11/15
 */

public interface BaseView {
    void fail(String errorMsg);
    // 扫描状态更新，根据需要更新
    void scanStatusShow(int current,int maxsize,long size,String path);
}
