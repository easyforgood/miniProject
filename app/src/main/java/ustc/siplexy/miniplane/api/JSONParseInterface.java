package ustc.siplexy.miniplane.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ JSON 解析使用的接口
 * Created by 翔 on 2015/7/29.
 */
public interface JSONParseInterface<T>{
    public T parseJSONObject(JSONObject resultJson) throws JSONException;
    public T parseJSONArray(JSONArray resultJson);
}
