package ustc.siplexy.miniplane.api;/**
 * Created by �� on 2015/7/29.
 */

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.ErrorListener;

import ustc.siplexy.miniplane.api.httpclient.UIListenerInterface;

/**
 * Created with Android Studio.
 *
 * @Project:
 * @Package:
 * @Description: ͨ�ý���
 * @author:
 * @date:
 * @version: V1.0
 */
public class JSONResponse implements Response.Listener<JSONObject>,Response.ErrorListener{
    private UIListenerInterface uiListener=null;
    private JSONParseInterface parseJson=null;

    public JSONResponse(UIListenerInterface uiListener,
                             JSONParseInterface parseJson){
        this.uiListener=uiListener;
        this.parseJson=parseJson;

    }

    @Override
    public void onResponse(JSONObject jsonObject) {
        if (uiListener!=null && parseJson!=null){
            try {
                //�������ݣ��ص�UI�����ӿ�
                uiListener.onSuccess(parseJson.parseJSONObject(jsonObject));
            } catch (JSONException e) {
                Log.e("Api", "json parse wrong");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        uiListener.onError(error);
        Log.e("TAG", error.getMessage(), error);
    }
}
