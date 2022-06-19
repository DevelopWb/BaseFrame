package a3phone.of.com.main.mine;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.util.Map;

import a3phone.of.com.main.MainActivity;
import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseAppFragment;
import a3phone.of.com.main.bean.MultipleItem;
import a3phone.of.com.main.bean.MyMenuBean;
import a3phone.of.com.main.bean.UserBean;
import a3phone.of.com.main.entrance.LoginActivity;
import a3phone.of.com.main.net.CmdCallBack;
import a3phone.of.com.main.net.CmdUtil;
import a3phone.of.com.main.utils.HawkProperty;
import a3phone.of.com.main.utils.UserInfoManager;
import a3phone.of.disabled.basecomponent.utils.GsonTools;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;

/**
 * @aouther tobato
 * @description 描述
 * @date 2021/4/17 16:12
 */
public class MyCenterFragment extends BaseAppFragment<MyCenterPresent> implements MyCenterContract.ICenterView, View.OnClickListener {

    MyMenuAdapter myMenuAdapter, myMenuAdapter2;

    private ImageView mHeadImage;
    private TextView mNickname;
    private TextView mTelNumber;
    private RecyclerView mMenuRecycler, mMenuRecycler2;
    private View view;
    /**
     * 我的待办
     */
    private TextView mTodoAmountTv;
    private ConstraintLayout mTodoCl;
    private ConstraintLayout mMyApplyCl;

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
        mTodoAmountTv = (TextView) getView(R.id.todo_amount_tv);
        mTodoCl = (ConstraintLayout) getView(R.id.todo_cl);
        mTodoCl.setOnClickListener(this);
        mMyApplyCl = (ConstraintLayout) getView(R.id.my_apply_cl);
        mMyApplyCl.setOnClickListener(this);
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
                                View  modifyPwdView = LayoutInflater.from(mContext).inflate(R.layout.modify_pwd_v,null);
                                AlertDialog alertDialog = new AlertDialog.Builder(mContext,R.style.CustomDialog)
                                        .setCancelable(false)
                                        .setView(modifyPwdView).show();
                                getBaseAppActivity().setAlertDialogHeightWidth(alertDialog,0,0);
                                EditText oldEt = modifyPwdView.findViewById(R.id.old_pwd_et);
                                EditText newEt = modifyPwdView.findViewById(R.id.new_pwd_et);
                                EditText reNewEt = modifyPwdView.findViewById(R.id.re_new_pwd_et);

                                modifyPwdView.findViewById(R.id.close_dialog_iv).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alertDialog.dismiss();
                                    }
                                });
                                modifyPwdView.findViewById(R.id.commit_modify_tv).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String oldPwd = getBaseAppActivity().getTextViewValue(oldEt);
                                        String newPwd = getBaseAppActivity().getTextViewValue(newEt);
                                        String reNewPwd = getBaseAppActivity().getTextViewValue(reNewEt);

                                        if (TextUtils.isEmpty(oldPwd)) {
                                            ToastUtils.toast(mContext,"请输入旧密码");
                                            return;
                                        }
                                        if (TextUtils.isEmpty(newPwd)) {
                                            ToastUtils.toast(mContext,"请输入新密码");
                                            return;
                                        }
                                        if (TextUtils.isEmpty(reNewPwd)) {
                                            ToastUtils.toast(mContext,"请再次输入新密码");
                                            return;
                                        }
                                        if (!UserInfoManager.getUserPwd().equals(oldPwd)) {
                                            ToastUtils.toast(mContext,"旧密码输入错误,请重新输入");
                                            return;
                                        }
                                        if (!newPwd.equals(reNewPwd)) {
                                            ToastUtils.toast(mContext,"两次输入的新密码不一致,请重新输入");
                                            return;
                                        }
                                        // : 2022/6/19 调用修改密码的接口
                                        Map<String,Object> map = new ArrayMap<>();
                                        map.put("SessionID:",UserInfoManager.getSessionId());
                                        map.put("OldPassWord::",oldPwd);
                                        map.put("NewPassWord::",newPwd);
                                        CmdUtil.cmd("LoginHandlerAdapter", "UpdatePassword", map, new CmdCallBack() {
                                            @Override
                                            public void onSuccess(JSONObject result) {
                                                alertDialog.dismiss();
                                                startActivity(new Intent(mContext,LoginActivity.class));

                                            }

                                        });
                                    }
                                });


                                break;
                            case MyMenuBean.MENU_MODIFY_SIGN:
                                ToastUtils.toast(mContext, "1");
                                break;
                            case MyMenuBean.MENU_MODIFY_SUGGESTION:
                                ToastUtils.toast(mContext, "2");
                                break;
                            case MyMenuBean.MENU_MODIFY_CLEAR:
                                ToastUtils.toast(mContext, "3");
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
                            case MyMenuBean.MENU_QUIT_CURRENT_ACCOUNT:
                             getBaseAppActivity().showAlertDialog("是否退出当前账户？", "确定", "取消", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     Map<String,Object> map = new ArrayMap<>();
                                     map.put("SessionID:",UserInfoManager.getSessionId());
                                     CmdUtil.cmd("LoginHandlerAdapter", "UnLogin", map, new CmdCallBack() {
                                         @Override
                                         public void onSuccess(JSONObject result) {
                                            UserInfoManager.clearUserData();
                                             startActivity(new Intent(mContext, LoginActivity.class));
                                         }

                                     });
                                 }
                             });
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
        if (!UserInfoManager.isLogin()) {
            return;
        }
        switch (v.getId()) {
            case R.id.headImage:
                //用户信息设置
                break;
            case R.id.todo_cl:
                // TODO: 2022/6/17 待处理
                break;
            case R.id.my_apply_cl:
                // TODO: 2022/6/17 申请历史
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
