package ustc.siplexy.miniplane.api.httpclient;

        import android.app.Activity;
        import android.content.Context;
        import android.view.View;
        import android.widget.BaseAdapter;
        import android.widget.ListView;

        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.Volley;

/**
 * Created by �� on 2015/7/28.
 */
public interface UIListenerInterface<T> {
        /**
         * @TODO �ɹ�ʱ����UI
         * @param datas ���������
         */
        public void onSuccess(T datas);
        public void onError(VolleyError error);
}
