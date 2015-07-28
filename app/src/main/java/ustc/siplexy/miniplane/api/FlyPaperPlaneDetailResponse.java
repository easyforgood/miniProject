package ustc.siplexy.miniplane.api;/**
 * Created by Ïè on 2015/7/29.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class FlyPaperPlaneDetailResponse implements JSONParseInterface<String> {

    @Override
    public String parseJSONObject(JSONObject resultJson) throws JSONException {
        String data=resultJson.getString("data");

        return data;
    }

    @Override
    public String parseJSONArray(JSONArray resultJson) {
        return null;
    }
}
