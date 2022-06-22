package a3phone.of.com.main.base.customview.a3levelcombo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import a3phone.of.com.main.R;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2022/6/12 16:30
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/12 16:30
 */
public class A3LevelLableRv extends RecyclerView {

    private LinearLayoutManager linearLayoutManager;
    private A3LevelLableAdapter a3LevelLableAdapter;

    private OnItemClickCallBack onItemClickCallBack;


    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public A3LevelLableRv(Context context) {
        super(context);
        initView(context);
    }

    public A3LevelLableRv(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public A3LevelLableRv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }



    private void initView(Context context) {
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false);
        a3LevelLableAdapter = new A3LevelLableAdapter(R.layout.a3_level_combo_item);
        setLayoutManager(linearLayoutManager);
        setAdapter(a3LevelLableAdapter);
        a3LevelLableAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (onItemClickCallBack != null) {
                    onItemClickCallBack.onItemClicked(adapter,position);
                }
            }
        });
    }

    public void  setData(List<String> arrays){
        if (a3LevelLableAdapter != null) {
            a3LevelLableAdapter.setNewData(arrays);
        }
    }


}
