package mvp.bean;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;



/**
 * Created by ld on 2017/11/15.
 *
 * @author ld
 * @date 2017/11/15
 */

public class NeedBeanRequest {
    private Context context;
    private static NeedBeanRequest Instance = null;
    public static NeedBeanRequest Instance(Context context){
        if(Instance == null){
            Instance = new NeedBeanRequest(context);
        }
        return Instance;
    }
    private NeedBeanRequest(Context context){
        this.context = context;
    }
    String url = "http://gank.io/api/data/Android/10/1";
    public String getJson(StringCallback callback) throws Exception{
        String result = "";
        OkHttpUtils.get()
                .url(url)
                .id(100)
                .tag(context)
                .build()
                .execute(callback);
        result = callback.toString();
        return result;
    }
}
