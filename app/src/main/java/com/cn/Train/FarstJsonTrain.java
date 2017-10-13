package com.cn.Train;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cn.Bean.JsonEntity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by ld on 2017/10/13.
 */

public class FarstJsonTrain {
    public static void entity(String name){
        int age = 1;
        JsonEntity entity = new JsonEntity(name,age);
        String jsonStr = JSON.toJSONString(entity);
        Logger.json(jsonStr);
    }
    public static void list(){
        List<JsonEntity> list = new ArrayList<>();
        JsonEntity jsonEntity1 = new JsonEntity("listtoJsonName1",1);
        JsonEntity jsonEntity2 = new JsonEntity("listtoJsonName2",2);
        list.add(jsonEntity1);list.add(jsonEntity2);
        String jsonStr = JSON.toJSONString(list);
        Logger.json(jsonStr);
    }
    public static void bytes(String arrayAyy){
        JSONArray array = JSONArray.parseArray(arrayAyy);
        Logger.w("数组：" + array);
        Logger.w("数组长度：" + array.size());
        Collection collection = new Vector();
        collection.add(null);
        array.removeAll(collection);
        Logger.w("数组：" + array);
        Logger.w("数组长度：" + array.size());
    }
    public static void comlpex(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("username","chringLi");
        map.put("age",23);
        map.put("sex","男");
        // map集合
        HashMap<String, Object> temp = new HashMap<String, Object>();
        temp.put("name", "xiaohong");
        temp.put("age", "23");
        map.put("girlInfo", temp);
        // list集合
        List<String> list = new ArrayList<String>();
        list.add("爬山");
        list.add("骑车");
        list.add("旅游");
        map.put("hobby", list);
        String jsonStr = JSON.toJSONString(map);
        Logger.d("复杂数据类型"+jsonStr);
    }
}
