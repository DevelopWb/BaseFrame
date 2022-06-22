package a3phone.of.com.main.xietong.sortLable;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseAppActivity;
import a3phone.of.disabled.basecomponent.mvp.BasePresenter;

/**
 * @aouther tobato
 * @description 描述  标签排序
 * @date 2022/6/22 16:25
 */
public class SortLableActivity extends BaseAppActivity {


    private RecyclerView mRecyclerview;
    private SortLableAdapter sortLableAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutView() {
        return R.layout.recycleview_layout_no_refresh;
    }

    @Override
    public void initView() {

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        sortLableAdapter = new SortLableAdapter(R.layout.sort_lable_item,getTestData());
        LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(manager);
        mRecyclerview.setAdapter(sortLableAdapter);
        sortLableAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String aa = (String) adapter.getItem(position);
            }
        });
    }

    @Override
    public void initData() {
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(sortLableAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerview);

// 开启拖拽
        sortLableAdapter.enableDragItem(itemTouchHelper);
        sortLableAdapter.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                sortLableAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onSuccess(String tag, Object o) {

    }
}
