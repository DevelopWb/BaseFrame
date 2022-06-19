package a3phone.of.com.main.mine.sign;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import a3phone.of.com.main.R;
import a3phone.of.com.main.base.BaseAppActivity;
import a3phone.of.com.main.base.BaseAppPresent;
import a3phone.of.com.main.base.customview.GestureSignatureView;
import a3phone.of.com.main.mine.MyCenterPresent;
import a3phone.of.com.main.net.CmdCallBack;
import a3phone.of.com.main.net.CmdUtil;
import a3phone.of.com.main.utils.CalendarUtil;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.disabled.basecomponent.mvp.BasePresenter;
import a3phone.of.disabled.basecomponent.utils.FileCacheUtils;
import a3phone.of.disabled.basecomponent.utils.GsonTools;
import a3phone.of.disabled.basecomponent.utils.ImageLoadUtil;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;

/**
 * @aouther tobato
 * @description 描述  电子签名
 * @date 2022/6/19 15:48
 */
public class SignActivity extends BaseAppActivity<MyCenterPresent> implements View.OnClickListener, BaseIView {

    /**
     * 自行上传
     */
    private RadioButton mUploadSignRb;
    private ImageView mUploadSignIv;
    /**
     * 上传
     */
    private TextView mUploadSignTv;
    /**
     * 在线绘制
     */
    private RadioButton mDrawSignRb;
    private ImageView mDrawSignIv;
    /**
     * 绘制
     */
    private TextView mUploadDrawTv;
    private GestureSignatureView signatureView;
    private AlertDialog alertDialog;

    @Override
    protected MyCenterPresent createPresenter() {
        return new MyCenterPresent();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_sign;
    }

    @Override
    public void initView() {
        setTitleName("电子签名设置");
        mUploadSignRb = (RadioButton) findViewById(R.id.upload_sign_rb);
        mUploadSignIv = (ImageView) findViewById(R.id.upload_sign_iv);
        mUploadSignTv = (TextView) findViewById(R.id.upload_sign_tv);
        mUploadSignTv.setOnClickListener(this);
        mDrawSignRb = (RadioButton) findViewById(R.id.draw_sign_rb);
        mDrawSignIv = (ImageView) findViewById(R.id.draw_sign_iv);
        mUploadDrawTv = (TextView) findViewById(R.id.upload_draw_tv);
        mUploadDrawTv.setOnClickListener(this);
        initRadioStatus(0);
        mUploadSignRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    initRadioStatus(0);
                } else {
                    initRadioStatus(1);
                }
            }
        });
        mDrawSignRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    initRadioStatus(1);
                } else {
                    initRadioStatus(0);
                }
            }
        });
    }

    private void initRadioStatus(int i) {
        mUploadSignRb.setChecked(false);
        mDrawSignRb.setChecked(false);
        switch (i) {
            case 0:
                mUploadSignRb.setChecked(true);
                break;
            case 1:
                mDrawSignRb.setChecked(true);
                break;
            default:
                break;
        }

    }

    @Override
    public void initData() {

    }


    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            case BaseAppPresent.UPLOAD_IMAGE_TAG:
                ToastUtils.toast(mContext, "配置成功");
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.upload_sign_tv:
                initRadioStatus(0);
                choseImage(0, SignActivity.this, 1);
                break;
            case R.id.upload_draw_tv:
                initRadioStatus(1);

                View signView = LayoutInflater.from(mContext).inflate(R.layout.sign_v, null);
                alertDialog = new AlertDialog.Builder(mContext, R.style.CustomDialog)
                        .setCancelable(false)
                        .setView(signView).show();
                setAlertDialogHeightWidth(alertDialog, 0, 0);
                signView.findViewById(R.id.close_dialog_iv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                signatureView = signView.findViewById(R.id.gsv_signature);
                signView.findViewById(R.id.clear_sign_tv).setOnClickListener(this);
                signView.findViewById(R.id.confirm_sign_tv).setOnClickListener(this);
                break;

            case R.id.clear_sign_tv:
                signatureView.clear();
                break;
            case R.id.confirm_sign_tv:
                if (signatureView.getTouched()) {
                    try {
                        String fileName = String.format("%s%s", String.valueOf(System.currentTimeMillis()), FileCacheUtils.SIGN_PIC_NAME);
                        String signPath = FileCacheUtils.getAppImagePath(true) + fileName;
                        //保存到本地
                        signatureView.save(signPath);
                        if (mDrawSignIv != null) {
                            ImageLoadUtil.loadImageNoCache(mContext, signPath, mDrawSignIv);
                        }

                        mPresenter.uploadImage(signPath, BaseAppPresent.UPLOAD_IMAGE_TAG);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    alertDialog.dismiss();
                } else {
                    ToastUtils.toast(mContext, "请签名！");
                }
                break;
        }
    }

    @Override
    protected void selectedPicsAndEmpressed(List icons) {
        super.selectedPicsAndEmpressed(icons);
        if (icons != null) {
            if (!icons.isEmpty()) {
                String picPath = (String) icons.get(0);
                ImageLoadUtil.loadImage(mContext, picPath, mUploadSignIv);
                // TODO: 2022/6/19 将图片上传
                mPresenter.uploadImage(picPath, BaseAppPresent.UPLOAD_IMAGE_TAG);

            }
        }
    }
}
