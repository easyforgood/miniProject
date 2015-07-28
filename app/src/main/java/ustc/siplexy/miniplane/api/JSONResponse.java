package ustc.siplexy.miniplane.api;/**
 * Created by Ïè on 2015/7/29.
 */

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import ustc.siplexy.miniplane.ustc.siplexy.miniplane.httpclient.UIListenerInterface;

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
public class JSONResponse implements Response.Listener<JSONObject>{
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
                uiListener.onSuccess(parseJson.parseJSONObject(jsonObject));
            } catch (JSONException e) {
                Log.e("Api", "json parse wrong");
                e.printStackTrace();
            }
        }
    }
}
