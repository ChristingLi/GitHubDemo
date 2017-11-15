package com.cn.shopping.bean;

import com.cn.shopping.bean.GoodsBean;

import java.util.List;

/**
 * Created by ld on 2017/11/9.
 *
 * @author ld
 * @date 2017/11/9
 */

public class CatograyBean {
    private String kind;
    private List<GoodsBean> list;
    private int count;

    @Override
    public String toString() {
        return "CatograyBean{" +
                "kind='" + kind + '\'' +
                ", list=" + list +
                ", count=" + count +
                '}';
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<GoodsBean> getList() {
        return list;
    }

    public void setList(List<GoodsBean> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
