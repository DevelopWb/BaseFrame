package com.juntai.wisdom.project.net;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by tony on 16/3/13.
 */
public class HttpUtil {
    //public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    public static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build();

    private static OkHttpClient clientQuick = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build();

    public static void post(String url, String json, CmdCallBack callback) throws IOException
    {
        RequestBody body = RequestBody.create(MEDIA_TYPE, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void postShortRequest(String url, String json, CmdCallBack callback) throws IOException
    {
        RequestBody body = RequestBody.create(MEDIA_TYPE, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        clientQuick.newCall(request).enqueue(callback);
    }

    public static void post(String url, byte[] fileByte, CmdCallBack callback) throws IOException {

        RequestBody body = RequestBody.create(MEDIA_TYPE, fileByte);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //多图片上传，加参数
    public static void post(String url, String folderName, List<File> imageFiles, CmdCallBack callback) throws IOException {
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        multipartBodyBuilder.setType(MultipartBody.FORM);
        multipartBodyBuilder.addFormDataPart("ServerFolderName", folderName);
        //遍历imageFiles中所有图片绝对路径到builder，并约定key如“file”作为后台接受多张图片的key
        if (imageFiles != null){
            for (File file : imageFiles) {
                multipartBodyBuilder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
            }
        }
        //构建请求体
        RequestBody requestBody = multipartBodyBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
