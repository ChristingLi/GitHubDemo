package mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cn.github.R;

import java.util.ArrayList;
import java.util.List;

import mvp.bean.NeedBean;
import mvp.presenter.PresenterImpl;
import mvp.view.MVPView;

/**
 * Created by ld on 2017/11/15.
 *
 * @author ld
 * @date 2017/11/15
 */

public class MvpActivity  extends AppCompatActivity implements MVPView{
    private PresenterImpl presenter;
    private List<NeedBean.ResultsBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_test);
        presenter = new PresenterImpl(this,this);
        presenter.needBean();
    }

    @Override
    public void returnINeedMes(List<NeedBean.ResultsBean> needBean) {
        mList.clear();
        mList.addAll(needBean);
    }
}
