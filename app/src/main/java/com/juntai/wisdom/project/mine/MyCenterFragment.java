package com.juntai.wisdom.project.mine;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.juntai.disabled.basecomponent.mvp.BaseIView;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.wisdom.project.R;
import com.juntai.wisdom.project.base.BaseAppFragment;
import com.juntai.wisdom.project.bean.MultipleItem;
import com.juntai.wisdom.project.bean.MyMenuBean;
import com.juntai.wisdom.project.utils.GridDividerItemDecoration;
import com.juntai.wisdom.project.utils.UserInfoManager;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/4/17 16:12
 */
public class MyCenterFragment extends BaseAppFragment<MyCenterPresent> implements BaseIView, View.OnClickListener {

    MyMenuAdapter myMenuAdapter,myMenuAdapter2;

    private ImageView mHeadImage;
    private TextView mNickname;
    private TextView mTelNumber;
    private RecyclerView mMenuRecycler,mMenuRecycler2;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_center;
    }

    @Override
    protected void initView() {
        mHeadImage = getView(R.id.headImage);
        mHeadImage.setOnClickListener(this);
        mNickname = getView(R.id.nickname_tv);
        mTelNumber = getView(R.id.company_name_tv);
        mMenuRecycler = getView(R.id.menu_recycler);
        mMenuRecycler2 = getView(R.id.menu_recycler_2);
        myMenuAdapter = new MyMenuAdapter(mPresenter.getMenuBeans());
        myMenuAdapter2 = new MyMenuAdapter(mPresenter.getMenuBeans2());
        getBaseActivity().initRecyclerview(mMenuRecycler, myMenuAdapter, LinearLayoutManager.VERTICAL);
        getBaseActivity().initRecyclerview(mMenuRecycler2, myMenuAdapter2, LinearLayoutManager.VERTICAL);
        myMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem multipleItem = (MultipleItem) adapter.getItem(position);
                switch (multipleItem.getItemType()) {
                    case MultipleItem.ITEM_MYCENTER_MENUS:
                        MyMenuBean item = (MyMenuBean) multipleItem.getObject();
                        switch (item.getName()) {
                            case MyMenuBean.MENU_MODIFY_PWD:
                                ToastUtils.toast(mContext,"修改密码");
                                break;
                            case MyMenuBean.MENU_MODIFY_SIGN:
                                ToastUtils.toast(mContext,"1");
                                break;
                            case MyMenuBean.MENU_MODIFY_SUGGESTION:
                                ToastUtils.toast(mContext,"2");
                                break;
                            case MyMenuBean.MENU_MODIFY_CLEAR:
                                ToastUtils.toast(mContext,"3");
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }

            }
        });
        myMenuAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultipleItem multipleItem = (MultipleItem) adapter.getItem(position);
                switch (multipleItem.getItemType()) {
                    case MultipleItem.ITEM_MYCENTER_MENUS:
                        MyMenuBean item = (MyMenuBean) multipleItem.getObject();
                        switch (item.getName()) {
                            case MyMenuBean.MENU_MODIFY_ADVISER:
                                ToastUtils.toast(mContext,"4");
                                break;
                            case MyMenuBean.MENU_MODIFY_CLEAR:
                                ToastUtils.toast(mContext,"5");
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    protected void lazyLoad() {
        getBaseAppActivity().mImmersionBar.statusBarColor(R.color.gray_light)
                .statusBarDarkFont(true)
                .init();
    }

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }


    @Override
    public void onClick(View v) {
        if (!UserInfoManager.isLogin()){
            return;
        }
        switch (v.getId()) {
            case R.id.headImage:
                //用户信息设置
                break;
        }
    }

    @Override
    public void onSuccess(String tag, Object o) {
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
