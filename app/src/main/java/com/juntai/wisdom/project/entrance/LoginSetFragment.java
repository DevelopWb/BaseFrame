package com.juntai.wisdom.project.entrance;

import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseAppFragment;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 22:10
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 22:10
 */
public class LoginSetFragment extends BaseAppFragment<EntrancePresent> implements BaseIView {
    @Override
    protected EntrancePresent createPresenter() {
        return new EntrancePresent();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_login_set;
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
