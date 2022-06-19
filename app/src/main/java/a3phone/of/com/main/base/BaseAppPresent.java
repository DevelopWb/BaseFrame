package a3phone.of.com.main.base;

import android.text.TextUtils;
import android.util.ArrayMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import a3phone.of.com.main.net.CmdCallBack;
import a3phone.of.com.main.net.CmdUtil;
import a3phone.of.com.main.utils.CalendarUtil;
import a3phone.of.disabled.basecomponent.mvp.BasePresenter;
import a3phone.of.disabled.basecomponent.mvp.IModel;
import a3phone.of.disabled.basecomponent.mvp.BaseIView;
import a3phone.of.com.main.utils.UserInfoManager;

import a3phone.of.disabled.basecomponent.utils.ImageLoadUtil;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;
import okhttp3.MultipartBody;

/**
 * @Author: tobato
 * @Description: 作用描述
 * @CreateDate: 2020/6/3 8:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/3 8:38
 */
public abstract class BaseAppPresent<M extends IModel, V extends BaseIView> extends BasePresenter<M, V> {

    public  final static  String UPLOAD_IMAGE_TAG = "UPLOAD_IMAGE_TAG";


    public  void  uploadImage(String filePath,String tag){
        // TODO: 2022/6/19 图片上传到服务器
        if (TextUtils.isEmpty(filePath)) {
            if (getView() != null) {
                getView().onError(tag,"文件路径不能为空");
            }
            return;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            if (getView() != null) {
                getView().onError(tag,"文件不存在");
            }
            return;
        }
        CmdUtil.uploadFile(filePath, new CmdCallBack() {
            @Override
            public void onSuccess(JSONObject result) {
                Map<String, Object> map = new ArrayMap<>();
                map.put("IMG", String.format("%s/%s", CalendarUtil.getServerFolderName(), file.getName()));
                CmdUtil.cmd("A3OFAppAdapter", "UPUserSignImg", map, new CmdCallBack() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        if (getView() != null) {
                            getView().onSuccess(tag,result);
                        }

                    }

                });
            }
        });
    }

}
