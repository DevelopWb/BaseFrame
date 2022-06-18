package a3phone.of.com.main.net;


import a3phone.of.com.main.MyApp;
import a3phone.of.com.main.utils.UserInfoManager;
import a3phone.of.disabled.basecomponent.utils.GsonTools;
import a3phone.of.com.main.utils.HawkProperty;
import a3phone.of.disabled.basecomponent.utils.LogUtil;
import a3phone.of.disabled.basecomponent.utils.ToastUtils;

import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by tony on 16/3/13.
 */
public class CmdUtil
{
    //多参数上传
    public static void cmd(String handlerName,String queryType,Map<String,Object> parameters,CmdCallBack cmdCallBack)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("HandlerName",handlerName);
            json.put("QueryType",queryType);
            if(parameters==null){
                json.put("Parameters","{}");
            }else{
                json.put("Parameters", GsonTools.createGsonString(parameters));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        cmdCallBack.setParameters(json);
        cmdCallBack.setCurrInstance(cmdCallBack);


        exeCmd(json,cmdCallBack);
    }


    //json参数上传
    public static void cmd(String handlerName, String queryType, JSONObject parameters, CmdCallBack cmdCallBack)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("HandlerName",handlerName);
            json.put("QueryType",queryType);
            if(parameters==null){
                json.put("Parameters","");
            }else{
                json.put("Parameters", parameters.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        cmdCallBack.setParameters(json);
        cmdCallBack.setCurrInstance(cmdCallBack);

        exeCmd(json,cmdCallBack);
    }

    public static  void exeCmd(JSONObject json,CmdCallBack cmdCallBack)
    {
        try {
            json.put("SessionID", UserInfoManager.getSessionId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try{

            String jsonStr = json.toString();
            LogUtil.e(jsonStr);
            HttpUtil.post(UserInfoManager.getServerAddr()+"/SystemHandler.axd?ClientType=Android",jsonStr,cmdCallBack);
        }catch (IOException ex){
            ToastUtils.error(MyApp.app,"HTTP错误:无法连接服务器！");
        }
    }

//    public static  void exeCmdShortRequest(JSONObject json,CmdCallBack cmdCallBack)
//    {
//        if(A3Application.getCurSession().getSessionID()==null || A3Application.getCurSession().getSessionID().equals("")){
//            json.put("SessionID", "");
//        }else {
//            json.put("SessionID", A3AppLocal.GetSessionID());
//        }
//        try{
//            String jsonStr = json.toString();
//            HttpUtil.postShortRequest(Constant.BASEURL+"SystemHandler.axd?ClientType=Android",jsonStr,cmdCallBack);
//        }catch (IOException ex){
//            ToastUtil.showToast(A3Application.getInstance(),"HTTP错误:无法连接服务器！");
//        }
//    }
//
//    //文件上传
//    public static void cmd(byte[] fileByte, CmdCallBack cmdCallBack,String headImg)
//    {
//        //创建File
////        File file = new File(filePath);
//        try{
//            String URL=Constant.BASEURL+"UpLoad.axd?HandlerType=Add&ServerFileName={HeadImg}.png&ServerFolderName=AppHeadImg";
//            URL=URL.replace("{HeadImg}",headImg);
//            HttpUtil.post(URL, fileByte, cmdCallBack);
//        }catch (IOException ex){
//            ToastUtil.showToast(A3Application.getInstance(),"HTTP错误:无法连接服务器！");
//        }
//    }
//    //文件上传
//    public static void cmd(byte[] fileByte, CmdCallBack cmdCallBack,String imgName,String foldName)
//    {
//        //创建File
////        File file = new File(filePath);
//        try{
//            String URL=Constant.BASEURL+"UpLoad.axd?HandlerType=Add&ServerFileName={0}&ServerFolderName={1}";
//            URL=URL.replace("{0}",imgName);
//            URL=URL.replace("{1}",foldName);
//            HttpUtil.post(URL, fileByte, cmdCallBack);
//        }catch (IOException ex){
//            ToastUtil.showToast(A3Application.getInstance(),"HTTP错误:无法连接服务器！");
//        }
//    }
//
//    //多图片加参数上传
//    //文件上传
//    public static void cmd(String folderName, List<File> imageFiles,CmdCallBack cmdCallBack)
//    {
//        try{
//            String URL = Constant.BASEURL+"ImageUpload.axd";
//            HttpUtil.post(URL, folderName, imageFiles,cmdCallBack);
//        }catch (IOException ex){
//            ToastUtil.showToast(A3Application.getInstance(),"HTTP错误:无法连接服务器！");
//        }
//    }
}
