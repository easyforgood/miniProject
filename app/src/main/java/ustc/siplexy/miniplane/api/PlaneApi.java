/**
 *
 * test
 *
 * ������
 */

package ustc.siplexy.miniplane.api;

import android.util.Log;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ustc.siplexy.miniplane.models.PaperPlaneDetail;
import ustc.siplexy.miniplane.api.httpclient.UIListenerInterface;
import ustc.siplexy.miniplane.api.httpclient.VolleyService;

/**
 * //�ѷ�����
 * Created by siplexypeng on 2015/7/28.
 */
public class PlaneApi {
     static final String reqPath="http://104.236.124.234:8080/fly-plane";

    /*
    @TODO:��ɻ�
     */
    public static void pickPlane(UIListenerInterface uiListener){

    }



    /*
    @TODO:��ɻ����� �鿴����
     */
    public static void pickPlaneDetail(Map<String,String> params,UIListenerInterface uiListener){



    }

    public static void testApi(Map params, final UIListenerInterface uiListenerInterface){
        VolleyService.requestStringByGET("http://baidu.com", new Response.Listener<String>() {

            @Override
            public void onResponse(String s) {
                Log.i("test",s);
                uiListenerInterface.onSuccess(s);
            }
        },null);
    }



    /*
        @TODO:��������json���� paperplaneDetail
     */
    public static List<PaperPlaneDetail> parsePaperPlaneDetail(JSONObject resultJson) throws JSONException {
        JSONArray data=resultJson.getJSONArray("data");
        List<PaperPlaneDetail> list=null;
        if (data==null){
            return null;
        }

        list=new ArrayList<PaperPlaneDetail>();

        int dataLen=data.length();
        for(int dataPos=0;dataPos<=dataLen;dataPos++){
            PaperPlaneDetail item=new PaperPlaneDetail();
            JSONObject jsonItem=data.getJSONObject(dataPos);
            //�������
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

    /*
        @TODO: ��������ӿ�ʵ��
     */
    private class PlaneApiResponse implements Response.Listener<JSONObject>{
        UIListenerInterface<List<PaperPlaneDetail>> uiListener=null;
        public PlaneApiResponse(UIListenerInterface uiListener){
            this.uiListener=uiListener;
        }

        @Override
        public void onResponse(JSONObject jsonObject) {
            try {
                uiListener.onSuccess(parsePaperPlaneDetail(jsonObject));
            } catch (JSONException e) {
                Log.e("Api","json parse wrong");
                e.printStackTrace();
            }

        }
    }



}
