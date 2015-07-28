package ustc.siplexy.miniplane.ustc.siplexy.miniplane.httpclient;

        import android.app.Activity;
        import android.content.Context;
        import android.view.View;
        import android.widget.BaseAdapter;
        import android.widget.ListView;

/**
 * Created by Ïè on 2015/7/28.
 */
public interface UIListenerInterface<T> {
        public void onSuccess(T datas);
}
