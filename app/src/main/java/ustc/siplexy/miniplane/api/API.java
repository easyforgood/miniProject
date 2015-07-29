package ustc.siplexy.miniplane.api;/**
 * Created by 翔 on 2015/7/28.
 */

import android.widget.ImageView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.ErrorListener;

import ustc.siplexy.miniplane.api.httpclient.UIListenerInterface;
import ustc.siplexy.miniplane.api.httpclient.VolleyService;
import ustc.siplexy.miniplane.models.PaperPlane;
import ustc.siplexy.miniplane.models.PaperPlaneDetail;

/**
 * Created with Android Studio.
 * @TODO 提供给 前端的接口
 * @Project:
 * @Package:
 * @Description:
 * @author: siplexypeng 朋翔
 * @date:
 * @version: V1.0
 */
public class API {
    public static final String SUCCESS="success";
    public static final String ZERO="0";

    /**
     * @TODO 获取热门飞机
     *
     * @param amount 请求信息的数量
     * @param offset 请求的偏移量
     * @param uiListener List<PaperPlane>类型
     *
     */
    public static void pickHotPlane(Integer amount,
                                    Integer offset,
                                    UIListenerInterface<List<PaperPlane>> uiListener){
        //start
        String reqUrl="hot-planes";
        HashMap<String,String> params= new HashMap<String,String>();
        params.put("amount",amount == null? API.ZERO: amount.toString());
        params.put("offset", offset == null ? API.ZERO : amount.toString());
        JSONResponse jsonResponse=new JSONResponse(uiListener,new HotPlaneResponse());
        Response.Listener<JSONObject> responseListener=jsonResponse;

        Response.ErrorListener errorListener=jsonResponse;

        VolleyService.requestJsonByGET(reqUrl,params,responseListener,errorListener);

    }

    /**
     * @TODO  获取文章段落 请求飞机段落
     * @param story_id 所在故事id
     * @param amount 请求数量
     * @param offset 偏移量
     * @param uiListenerInterface List<PaperPlaneDetail>
     *
     */
    public static void pickPlaneDetail(Integer story_id,
                                       Integer amount,
                                       Integer offset,
                                       UIListenerInterface<List<PaperPlaneDetail>> uiListenerInterface){
        //start
        String reqUrl="plane/paragraphs";
        Map<String,String> params=new HashMap<String, String>();
        if (amount==null){
            amount=0;
        }
        if (offset == null){
            offset=0;
        }
        if (null==story_id){
            story_id=0;
        }
        if (story_id.equals(0))
            return;
        params.put("story_id",story_id.toString());
        params.put("amount",amount.toString());
        params.put("offset", offset.toString());
        Response.Listener<JSONObject> responseListener=
                new JSONResponse(uiListenerInterface,new PickPaperPlaneDetailResponse());
        VolleyService.requestJsonByGET(reqUrl, params, responseListener, null);
    }

    /**
     * @TODO  放飞飞机  发送段落
     * @param storyid 所在飞机的id
     * @param title 标题
     * @param content 文章内容内容
     * @param uiListener
     *
     */
    public static void flyPlaneDetail(Integer storyid,
                                      String title,
                                      String content,
                                      UIListenerInterface uiListener){
        //start
        String reqUrl="fly-plane";
        Map<String,String> params=new HashMap<String, String>();
        if (storyid==null){
            return ;
        }
        params.put("story_id",storyid.toString());
        params.put("title", title == null ? "" : title);
        params.put("content", content==null? "" : content);
        Response.Listener<JSONObject> responseListener=
                new JSONResponse(uiListener,new FlyPaperPlaneDetailResponse());
        VolleyService.requestJsonByPOST(reqUrl, params, responseListener, null);
    }

    /**
     * @TODO 异步请求图片
     * @param reqUrl 请求url
     * @param view 绑定的imageview
     * @param defaultImg 默认情况下的资源文件
     * @param faultImg 错误情况下的资源文件
     */
    public static void getImg(String reqUrl,ImageView view,int defaultImg,int faultImg){

        VolleyService.getImg(reqUrl,view,defaultImg,faultImg);
    }

}
