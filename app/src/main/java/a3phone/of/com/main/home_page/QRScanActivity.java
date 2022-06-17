package a3phone.of.com.main.home_page;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;

import a3phone.of.com.main.R;
import a3phone.of.disabled.basecomponent.base.BaseWebViewActivity;
import a3phone.of.disabled.basecomponent.mvp.BasePresenter;
import a3phone.of.disabled.basecomponent.utils.FileCacheUtils;
import a3phone.of.disabled.basecomponent.utils.JsonUtil;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;
import a3phone.of.com.main.base.BaseAppActivity;
import a3phone.king.zxing.CaptureHelper;
import a3phone.king.zxing.OnCaptureCallback;
import a3phone.king.zxing.ViewfinderView;
import a3phone.king.zxing.util.CodeUtils;
import a3phone.zhihu.matisse.Matisse;

import java.util.List;

public class QRScanActivity extends BaseAppActivity implements View.OnClickListener, OnCaptureCallback {
    private SurfaceView mSurfaceView;
    private ViewfinderView mViewfinderView;
    private ImageView mZxingPic;
    private ImageView mIvTorch;
    private ImageView mZxingBackBtn;
    private CaptureHelper mCaptureHelper;

    private int SELECT_PIC_RESULT = 1001;


    @Override
    public int getLayoutView() {
        return R.layout.activity_qrscan;
    }

    @Override
    public void initView() {
        getToolbar().setVisibility(View.GONE);
        mBaseRootCol.setFitsSystemWindows(false);
        mImmersionBar.reset().statusBarDarkFont(false).init();
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mViewfinderView = (ViewfinderView) findViewById(R.id.viewfinderView);
        mZxingPic = (ImageView) findViewById(R.id.zxing_pic);
        mZxingPic.setOnClickListener(this);
        mIvTorch = (ImageView) findViewById(R.id.ivTorch);
        mZxingBackBtn = (ImageView) findViewById(R.id.zxing_back_btn);
        mZxingBackBtn.setOnClickListener(this);
        mIvTorch.setOnClickListener(this);

        mCaptureHelper = new CaptureHelper(this, mSurfaceView, mViewfinderView, null);
        mCaptureHelper.setOnCaptureCallback(this);
        mCaptureHelper.characterSet("ISO-8859-1");
        mCaptureHelper.onCreate();
        mCaptureHelper.playBeep(true);
    }

    @Override
    public void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zxing_pic:
                choseImage(0, this, 1);
                break;
            case R.id.zxing_back_btn:
                finish();
                break;
            case R.id.ivTorch:
                mIvTorch.setSelected(!mIvTorch.isSelected());
                mCaptureHelper.getCameraManager().setTorch(mIvTorch.isSelected());
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PIC_RESULT && resultCode == Activity.RESULT_OK) {
            String content = CodeUtils.parseCode(Matisse.obtainPathResult(data).get(0));
            resolveQrcode(content);
        }
    }

    @Override
    protected void selectedPicsAndEmpressed(List icons) {
        super.selectedPicsAndEmpressed(icons);

        if (icons != null && icons.size() > 0) {
            String pic = (String) icons.get(0);
            String result = null;
            Bitmap bitmap = FileCacheUtils.getImageBitmap(pic);
            //“QRCODE_SCAN_TYPE ”和“ DATAMATRIX_SCAN_TYPE表示只扫描QR和Data Matrix的码
            HmsScanAnalyzerOptions options = new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.QRCODE_SCAN_TYPE, HmsScan.DATAMATRIX_SCAN_TYPE).setPhotoMode(true).create();
            HmsScan[] hmsScans = ScanUtil.decodeWithBitmap(mContext, bitmap, options);
            //处理扫码结果
            if (hmsScans != null && hmsScans.length > 0) {
                result = hmsScans[0].showResult;
                resolveQrcode(result);
            }else {
                ToastUtils.toast(mContext,"没有检测到有效的二维码");
            }

        }

    }

    /**
     * 解析二维码
     *
     * @param result
     */
    public void resolveQrcode(String result) {
        /**
         *  21960 菜优选的端口号
         */
        if (result.contains("juntaikeji") && result.contains("21960")) {
            //内部二维码
            String id = result.substring(result.lastIndexOf("=") + 1, result.length());
        } else {
            startActivity(new Intent(mContext, BaseWebViewActivity.class).putExtra("url", result));
        }
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCaptureHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mCaptureHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCaptureHelper.onDestroy();
        mCaptureHelper = null;
    }

    @Override
    protected boolean canCancelLoadingDialog() {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mCaptureHelper.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onResultCallback(String result) {
        String resultData = JsonUtil.transcoding(result);
        if (JsonUtil.isNumber(resultData)) {
            mCaptureHelper.onPause();
            mCaptureHelper.onResume();
        } else {
            resolveQrcode(resultData);
        }
        return true;
    }



    @Override
    public void onSuccess(String tag, Object o) {

    }

    @Override
    public void onError(String tag, Object o) {

    }
}