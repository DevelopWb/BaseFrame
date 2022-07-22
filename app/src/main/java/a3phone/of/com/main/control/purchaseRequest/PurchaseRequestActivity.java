package a3phone.of.com.main.control.purchaseRequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import a3phone.of.com.main.R;
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
    protected void getRvAdapterData() {
        baseQuickAdapter.setNewData(mPresenter.getPurchaseRequestData(getSupportFragmentManager(),false));
    }


}
