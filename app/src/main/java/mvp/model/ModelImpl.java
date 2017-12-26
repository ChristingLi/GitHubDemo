package mvp.model;

import com.cn.application.MyApplication;
import com.cn.github.R;
import com.cn.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import mvp.bean.NeedBean;
import mvp.bean.NeedBeanRequest;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by ld on 2017/11/15.
 *
 * @author ld
 * @date 2017/11/15
 */

public class ModelImpl implements MVPModel{
    @Override
    public List<NeedBean.ResultsBean> loadNeedBean() {
        try {
            NeedBeanRequest.Instance(MyApplication.getAppContext()).getJson(new GetJsonStringCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    /*get接口的自定义回调函数*/
    public class GetJsonStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {//showProgressDialog("");//显示进度加载框
            ToastUtil.showShortToast("数据加载中");
        }
        @Override
        public void onAfter(int id) {//dismissProgressDialog();//隐藏进度加载框
            ToastUtil.showShortToast("数据加载完成");
        }
        @Override
        public void onError(Call call, Exception e, int id) {
            ToastUtil.showShortToast(MyApplication.getAppContext().getResources().getString(R.string.login_again));
        }
        @Override
        public void onResponse(String response, int id) {
            Logger.d("TAG"+response);
            switch (id) {
                case 100://http
                    break;
                case 101://https
                    break;
                default:
            }
        }
        @Override
        public void inProgress(float progress, long total, int id) {
        }
    }
}
