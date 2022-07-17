package a3phone.of.com.main.multi;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public interface MultiContact {

    int BASE_RECYCLERVIEW_TYPE_MULTI = 0;



    String  PURCHASE_TYPE = "采购类型";
    String  PURCHASE_DELIVER_TIME = "期望交付时间";
    String  PURCHASE_REASON = "原因";
    String  PAY_TYPE = "支付方式";
    String  REMARK_PICS = "附件图片";
    String  REMARK = "备注说明";



    public interface  BaseNormalRvItemClick{

       void onItemChildClick(BaseQuickAdapter adapter, RecyclerView recyclerView, int position);

    }


}
