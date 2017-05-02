package weijiangtao.bwie.com.yuekaolian1.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by asus on 2017/5/2.
 * <p>
 * 未江涛
 */

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoaderConfiguration imageq=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(imageq);
        
        
    }
}
