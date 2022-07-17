package a3phone.of.com.main.control.purchaseRequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import a3phone.of.com.main.multi.BaseMultiActivity;

/**
 * @aouther tobato
 * @description 描述  采购申请
 * @date 2022/7/17 11:57
 */
public class PurchaseRequestActivity extends BaseMultiActivity {


    @Override
    public void initView() {
        super.initView();
//        setTitleName("采购申请");
    }

    @Override
    protected View getAdapterFootView() {
        return null;
    }

    @Override
    protected void getRvAdapterData() {
        baseQuickAdapter.setNewData(mPresenter.getPurchaseRequestData(getSupportFragmentManager(),false));
    }



}
