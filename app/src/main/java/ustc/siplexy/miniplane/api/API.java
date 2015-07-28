package ustc.siplexy.miniplane.api;/**
 * Created by 翔 on 2015/7/28.
 */

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.ErrorListener;

import ustc.siplexy.miniplane.ustc.siplexy.miniplane.httpclient.UIListenerInterface;
import ustc.siplexy.miniplane.ustc.siplexy.miniplane.httpclient.VolleyService;

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
public class API {
    public static final String SUCCESS="success";

    /**
     * @TODO  获取文章段落 请求飞机段落
     * @param amount 请求数量
     * @param offset 偏移量
     * @param uiListenerInterface ui更新接口
     * @param errorListener 错误提示接口
     */
    public static void pickPlaneDetail(Integer amount,
                                       Integer offset,
                                       UIListenerInterface uiListenerInterface,
                                       ErrorListener errorListener){
        //start
        String reqUrl="/plane/paragraphs";
        Map<String,String> params=new HashMap<String, String>();
        if (amount==null){
            amount=0;
        }
        if (offset == null){
            offset=null;
        }
        params.put("amount",amount.toString());
        params.put("offset", offset.toString());
        Response.Listener<JSONObject> responseListener=
                new JSONResponse(uiListenerInterface,new PickPaperPlaneDetailResponse());
        VolleyService.requestJsonByGET(reqUrl, params, responseListener, null);
    }

    /**
     *
     * @param storyid
     * @param title
     * @param content
     * @param uiListener
     * @param errorListener
     */
    public static void flyPlaneDetail(Integer storyid,
                                      String title,
                                      String content,
                                      UIListenerInterface uiListener,
                                      ErrorListener errorListener){
        //start
        String reqUrl="/fly-plane";
        Map<String,String> params=new HashMap<String, String>();
        if (storyid==null){
            return ;
        }
        params.put("story_id",storyid.toString());
        params.put("title", title.toString());
        params.put("content", content.toString());
        Response.Listener<JSONObject> responseListener=
                new JSONResponse(uiListener,new FlyPaperPlaneDetailResponse());
        VolleyService.requestJsonByPOST(reqUrl,params,responseListener,null);
    }

}
