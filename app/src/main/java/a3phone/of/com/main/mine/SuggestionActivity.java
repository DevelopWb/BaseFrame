package a3phone.of.com.main.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.hawk.Hawk;

import org.json.JSONObject;

import java.util.Map;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseAppActivity;
import a3phone.of.com.main.base.selectPics.SelectPhotosFragment;
import a3phone.of.com.main.bean.UserBean;
import a3phone.of.com.main.net.CmdCallBack;
import a3phone.of.com.main.net.CmdUtil;
import a3phone.of.com.main.utils.HawkProperty;
import a3phone.of.com.main.utils.UserInfoManager;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.utils.GsonTools;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;

/**
 * @aouther tobato
 * @description 描述 投诉建议
 * @date 2022/6/19 20:39
 */
public class SuggestionActivity extends BaseAppActivity<MyCenterPresent> implements BaseIView, SelectPhotosFragment.OnPhotoItemClick, View.OnClickListener {

    /**
     * 你想说点什么？
     */
    private EditText mContentEt;
    /**
     * 请留下你的联系方式
     */
    private EditText mContactEt;

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_suggestion;
    }

    @Override
    public void initView() {
        setTitleName("意见反馈");
        mContentEt = (EditText) findViewById(R.id.content_et);
        mContactEt = (EditText) findViewById(R.id.contact_et);
        findViewById(R.id.commit_tv).setOnClickListener(this);
        SelectPhotosFragment selectPhotosFragment = (SelectPhotosFragment) getSupportFragmentManager().findFragmentById(R.id.suggestion_select_pic_fg);
        selectPhotosFragment.setSpanCount(4);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {

    }

    @Override
    public void onVedioClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onPicClick(BaseQuickAdapter adapter, int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit_tv:
                // TODO: 2022/6/19 提交建议
                String content = getTextViewValue(mContentEt);
                String phone = getTextViewValue(mContactEt);
                if (TextUtils.isEmpty(content)) {
                    ToastUtils.toast(mContext,"请输入反馈的内容");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.toast(mContext,"请输入联系方式");
                    return;
                }
                Map<String,Object> map = new ArrayMap<>();
                map.put("FeedBack:",content);
                map.put("Phone:",phone);
                // TODO: 2022/6/19 只取ServerFolderName+'/'+ServerFileName 作为 该参数值

                map.put("IMG:","");
                CmdUtil.cmd("A3OFAppAdapter", "FeedBack", map , new CmdCallBack() {
                    @Override
                    public void onSuccess(JSONObject result) {

                    }
                });

                break;
            default:
                break;
        }
    }
}
