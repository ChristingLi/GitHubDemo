package mvp.view;

import java.util.List;

import mvp.bean.NeedBean;

/**
 * Created by ld on 2017/11/15.
 *
 * @author ld
 * @date 2017/11/15
 */

public interface MVPView {
    void returnINeedMes(List<NeedBean.ResultsBean> needBean);
}
