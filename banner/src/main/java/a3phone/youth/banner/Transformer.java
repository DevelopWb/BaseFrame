package a3phone.youth.banner;



import android.support.v4.view.ViewPager;

import a3phone.youth.banner.transformer.AccordionTransformer;
import a3phone.youth.banner.transformer.BackgroundToForegroundTransformer;
import a3phone.youth.banner.transformer.CubeInTransformer;
import a3phone.youth.banner.transformer.CubeOutTransformer;
import a3phone.youth.banner.transformer.DefaultTransformer;
import a3phone.youth.banner.transformer.DepthPageTransformer;
import a3phone.youth.banner.transformer.FlipHorizontalTransformer;
import a3phone.youth.banner.transformer.FlipVerticalTransformer;
import a3phone.youth.banner.transformer.ForegroundToBackgroundTransformer;
import a3phone.youth.banner.transformer.RotateDownTransformer;
import a3phone.youth.banner.transformer.RotateUpTransformer;
import a3phone.youth.banner.transformer.ScaleInOutTransformer;
import a3phone.youth.banner.transformer.StackTransformer;
import a3phone.youth.banner.transformer.TabletTransformer;
import a3phone.youth.banner.transformer.ZoomInTransformer;
import a3phone.youth.banner.transformer.ZoomOutSlideTransformer;
import a3phone.youth.banner.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends ViewPager.PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends ViewPager.PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
