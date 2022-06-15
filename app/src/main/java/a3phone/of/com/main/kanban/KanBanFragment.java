package a3phone.of.com.main.kanban;

import a3phone.of.com.main.R;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.com.main.base.BaseAppFragment;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/14 20:27
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/14 20:27
 */
public class KanBanFragment extends BaseAppFragment<KanBanPresent> implements BaseIView {
    @Override
    protected KanBanPresent createPresenter() {
        return new KanBanPresent();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.kanban_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String tag, Object o) {

    }
}
