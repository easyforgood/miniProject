package ustc.siplexy.miniplane;/**
 * Created by 翔 on 2015/7/29.
 */

import android.app.Application;

import ustc.siplexy.miniplane.api.httpclient.VolleyService;
import ustc.siplexy.miniplane.cache.ACache;

/**
 * Created with Android Studio.
 *
 * @Project:
 * @Package:
 * @Description:
 * @author:
 * @date:
 * @version: V1.0
 */
public class MainApplication extends Application {
    private static MainApplication instance;
    private static ACache cache;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        init();
    }

    private void init(){
        VolleyService.initVolley(this);
        cache=ACache.get(this);
    }
    public static MainApplication getInstance(){
        return instance;
    }
    public static ACache getACache(){
        return cache;
    }
}
