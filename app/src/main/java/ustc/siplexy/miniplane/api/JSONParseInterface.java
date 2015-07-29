package ustc.siplexy.miniplane.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ JSON ����ʹ�õĽӿ�
 * Created by �� on 2015/7/29.
 */
public interface JSONParseInterface<T>{
    public T parseJSONObject(JSONObject resultJson) throws JSONException;
    public T parseJSONArray(JSONArray resultJson);
}
