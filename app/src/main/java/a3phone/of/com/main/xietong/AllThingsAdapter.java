package a3phone.of.com.main.xietong;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Arrays;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.customview.a3levelcombo.A3LevelLableRv;
import a3phone.of.disabled.basecomponent.base.BaseActivity;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/17 22:05
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/17 22:05
 */
public class AllThingsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public AllThingsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        A3LevelLableRv a3LevelLableRv = helper.getView(R.id.a3_level_lable_rv);
        a3LevelLableRv.setData(Arrays.asList(new String[]{"test2", "test3", "test4"}));

    }


}
