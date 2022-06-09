package com.juntai.wisdom.project.utils.bannerImageLoader;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.juntai.disabled.basecomponent.utils.ImageLoadUtil;
import com.youth.banner.loader.ImageLoaderInterface;


public class GlideImageLoader implements ImageLoaderInterface<View> {
    long seek = 0;
    String  pathV;
    private OnFullScreenListener onFullScreenListener;

    public GlideImageLoader setOnFullScreenCallBack(OnFullScreenListener onFullScreenListener) {
        this.onFullScreenListener = onFullScreenListener;
        return this;
    }

    @Override
    public void displayImage(Context context, Object path, View view) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        //eg：

        BannerObject bannerObject = (BannerObject) path;
        String eventKey = bannerObject.getEventKey();

        if (BannerObject.BANNER_TYPE_IMAGE.equals(eventKey)) {
            ImageView imageView = (ImageView) view;
            //Glide 加载图片简单用法
            ImageLoadUtil.loadImageCache(context, (String) bannerObject.getEventObj(),imageView);
        } else if (BannerObject.BANNER_TYPE_VIDEO.equals(eventKey)) {
            pathV = (String) bannerObject.getEventObj();
        } else if (BannerObject.BANNER_TYPE_RTMP.equals(eventKey)) {
//            ImageView imageView = (ImageView) view;
//
//            ShopDetailBean.DataBean shopBean  = (ShopDetailBean.DataBean) bannerObject.getEventObj();
//            ImageLoadUtil.loadImageCache(context, shopBean.getCameraCover(),imageView);
        }


    }


    @Override
    public View createImageView(Context context, Object path) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        BannerObject bannerObject = (BannerObject) path;
        String eventKey = bannerObject.getEventKey();
        if (BannerObject.BANNER_TYPE_IMAGE.equals(eventKey)) {
            ImageView simpleDraweeView = new ImageView(context);
            return simpleDraweeView;
        } else if (BannerObject.BANNER_TYPE_VIDEO.equals(eventKey)) {
            return null;
        } else if (BannerObject.BANNER_TYPE_RTMP.equals(eventKey)) {
            // 流的封面图
            ImageView rtmpView = new ImageView(context);
            return rtmpView;
        }

        return null;

    }



    /**
     * 全屏得点击事件
     */
    public interface OnFullScreenListener{
        void onFullScreen();
    }
}
