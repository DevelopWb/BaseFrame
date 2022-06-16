package a3phone.of.com.main.control;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import a3phone.of.com.main.R;
import a3phone.of.com.main.bean.ControlMenuBean;
import a3phone.of.com.main.bean.ControlMenuEditListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @UpdateUser: 更新者
 */
public class ControlMenuAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private  OnControlMenuChildClick controlMenuChildClick;
    public ControlMenuAdapter(int layoutResId,OnControlMenuChildClick controlMenuChildClick) {
        super(layoutResId);
        this.controlMenuChildClick = controlMenuChildClick;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.control_menu_title_tv,item);
        RecyclerView recyclerView = helper.getView(R.id.control_menu_content_rv);
        ControlMenuChildAdapter menuChildAdapter = new ControlMenuChildAdapter(R.layout.control_child_menu);
        GridLayoutManager manager = new GridLayoutManager(mContext, 5);
        recyclerView.setAdapter(menuChildAdapter);
        recyclerView.setLayoutManager(manager);
        List<ControlMenuBean> arrays = new ArrayList<>();
        arrays.add(new ControlMenuBean("测试",""));
        menuChildAdapter.setNewData(arrays);
        menuChildAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (controlMenuChildClick != null) {
                    controlMenuChildClick.onMenuClick();
                }
            }
        });
    }


    public interface OnControlMenuChildClick{

        void onMenuClick();
    }
}
