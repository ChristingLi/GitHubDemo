package mvp.presenter;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mvp.bean.NeedBean;
import mvp.model.MVPModel;
import mvp.model.ModelImpl;
import mvp.view.MVPView;

/**
 * Created by ld on 2017/11/15.
 *
 * @author ld
 * @date 2017/11/15
 */

public class PresenterImpl implements MVPPresenter{
    private MVPModel moder;
    private MVPView view;
    private Context mContext;

    public PresenterImpl(Context context,MVPView mvpView){
        this.mContext = context;
        this.view = mvpView;
        moder = new ModelImpl();
    }
    @Override
    public void needBean() {
         List<NeedBean.ResultsBean> observable = moder.loadNeedBean();
    }
}
