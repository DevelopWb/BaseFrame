package com.juntai.wisdom.project.kanban;

import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseAppFragment;
import com.juntai.wisdom.project.xietong.XieTongPresent;

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
