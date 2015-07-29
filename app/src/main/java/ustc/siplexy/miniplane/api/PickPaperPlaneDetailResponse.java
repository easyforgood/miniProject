package ustc.siplexy.miniplane.api;/**
 * Created by Ïè on 2015/7/28.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
public class PickPaperPlaneDetailResponse implements JSONParseInterface<List<PaperPlaneDetail>> {


    @Override
    public List<PaperPlaneDetail> parseJSONObject(JSONObject resultJson) throws JSONException {
        JSONArray data=resultJson.getJSONArray("data");
        List<PaperPlaneDetail> list=null;
        if (data==null){
            return null;
        }

        list=new ArrayList<PaperPlaneDetail>();

        int dataLen=data.length();
        for(int dataPos=0;dataPos<dataLen;dataPos++){
            PaperPlaneDetail item=new PaperPlaneDetail();
            JSONObject jsonItem=data.getJSONObject(dataPos);
            //Ìí¼ÓÊý¾Ý
            item.setParagraph_id(jsonItem.getString(PaperPlaneDetail.PARAGRAPH_ID));
            item.setFavor_count(jsonItem.getJSONArray(PaperPlaneDetail.FAVOR_COUNT).length());
            item.setAuthor_nickname(jsonItem.getJSONObject(PaperPlaneDetail.AUTHOR).
                    getString(PaperPlaneDetail.AUTHOR_NICKNAME));
            item.setAuthor_picurl(jsonItem.getJSONObject(PaperPlaneDetail.AUTHOR).
                    getString(PaperPlaneDetail.AUTHOR_PICURL));
            item.setContent(jsonItem.getString(PaperPlaneDetail.CONTENT));
        }
        return list;
    }

    @Override
    public List<PaperPlaneDetail> parseJSONArray(JSONArray resultJson) {
        return null;
    }
}
