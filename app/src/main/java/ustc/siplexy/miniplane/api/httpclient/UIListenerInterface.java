package ustc.siplexy.miniplane.api.httpclient;

        import android.app.Activity;
        import android.content.Context;
        import android.view.View;
        import android.widget.BaseAdapter;
        import android.widget.ListView;

        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.Volley;

/**
 * Created by 翔 on 2015/7/28.
 */
public interface UIListenerInterface<T> {
        /**
         * @TODO 成功时更新UI
         * @param datas 传入的数据
         */
        public void onSuccess(T datas);
        public void onError(VolleyError error);
}
