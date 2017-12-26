package mvp.contract;

import com.cn.base.BaseModel;
import com.cn.base.BasePresenter;
import com.cn.base.BaseView;

import io.reactivex.Observable;

/**
 * Created by ld on 2017/11/15.
 *
 * @author ld
 * @date 2017/11/15
 */

public interface MainContract {
    interface View extends BaseView {
        void showDialog();

//        void onSucceed(Gank data);

        void onFail(String err);

        void hideDialog();
    }
    interface Model extends BaseModel {
//        Observable<Gank> getGank();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getGank();
    }
}
