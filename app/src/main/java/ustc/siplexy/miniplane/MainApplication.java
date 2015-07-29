package ustc.siplexy.miniplane;/**
 * Created by Ïè on 2015/7/29.
 */

import android.app.Application;

import ustc.siplexy.miniplane.api.httpclient.VolleyService;

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
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        init();
    }

    private void init(){
        VolleyService.initVolley(this);
    }
}
