package mvp.model;

import java.util.List;

import io.reactivex.Observable;
import mvp.bean.NeedBean;

/**
 * Created by ld on 2017/11/15.
 *
 * @author ld
 * @date 2017/11/15
 */

public interface MVPModel {
    List<NeedBean.ResultsBean> loadNeedBean();
}
