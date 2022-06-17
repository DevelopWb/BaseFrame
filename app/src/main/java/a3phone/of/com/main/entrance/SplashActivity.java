package a3phone.of.com.main.entrance;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.orhanobut.hawk.Hawk;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import a3phone.of.com.main.MainActivity;
import a3phone.of.com.main.TestActivity;
import a3phone.of.com.main.utils.HawkProperty;
import io.reactivex.functions.Consumer;

/**
 * @aouther Ma
 * @date 2019/3/13
 */
public class SplashActivity extends RxAppCompatActivity {
    String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION};

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new RxPermissions(this)
                .request(permissions)
                .delay(1, TimeUnit.SECONDS)
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //所有权限通过 看板
                        } else {
                            //有一个权限没通过
                        }
//                        startActivity(new Intent(SplashActivity.this, TestActivity.class));
//                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                        if (Hawk.contains(HawkProperty.SP_KEY_USER)) {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }else {
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                        }
                        finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                });
    }
}