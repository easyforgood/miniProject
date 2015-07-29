package ustc.siplexy.miniplane.api;/**
 * Created by �� on 2015/7/29.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ustc.siplexy.miniplane.models.PaperPlane;
import ustc.siplexy.miniplane.models.PaperPlaneDetail;

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
public class HotPlaneResponse implements JSONParseInterface<List<PaperPlane>> {



    @Override
    public List<PaperPlane> parseJSONObject(JSONObject resultJson) throws JSONException {
        JSONArray data=resultJson.getJSONArray("data");
        List<PaperPlane> list=null;
        if (data==null){
            return null;
        }

        list=new ArrayList<PaperPlane>();

        int dataLen=data.length();
        for(int dataPos=0;dataPos<dataLen;dataPos++){
            PaperPlane item=new PaperPlane();
            JSONObject jsonItem=data.getJSONObject(dataPos);
            //��������
            item.setStoryid(jsonItem.getString(PaperPlane.STORYID));
            item.setCreate_time(jsonItem.getString(PaperPlane.CREATE_TIME));
            item.setTotal_favours(jsonItem.getString(PaperPlane.TOTAL_FAVOURS));
            item.setTotal_collections(jsonItem.getString(PaperPlane.TOTAL_COLLECTIONS));
            list.add(item);

        }
        return list;
    }

    @Override
    public List<PaperPlane> parseJSONArray(JSONArray resultJson) {
        return null;
    }
}