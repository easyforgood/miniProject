package ustc.siplexy.miniplane.api;/**
 * Created by 翔 on 2015/7/28.
 */

import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ustc.siplexy.miniplane.Utils;
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

    public static void loginByQQ(String accessToken,UIListenerInterface<String> uiListener){
        String reqUrl="user/login";
        HashMap<String,String> params=new HashMap<>();
        if (accessToken==null){
            return ;
        }
        params.put("token",accessToken);
        JSONHandler jsonHandler=new JSONHandler(uiListener,new StringParse());
        VolleyService.requestJsonByPOST(reqUrl,params,jsonHandler,jsonHandler);
    }

    /**
     * @TODO 登陆 POST
     * @param phone
     * @param password
     * @param uiListener
     */
    public static void loginByPhone(String phone,
                                  String password,
                                  UIListenerInterface<String> uiListener){
        String reqUrl="user/login";
        HashMap<String,String> params=new HashMap<>();
        if(phone==null || password==null){
            return;
        }
        params.put("phone",phone);
        params.put("password", Utils.toMD5(password));

        JSONHandler jsonHandler =new JSONHandler(uiListener,new StringParse());
        Response.Listener<JSONObject> responseListener= jsonHandler;
        Response.ErrorListener errorListener= jsonHandler;
        VolleyService.requestJsonByPOST(reqUrl, params, responseListener, errorListener);
    }

    /**
     * @TODO　注册 POST
     * @param phone
     * @param password
     * @param uiListener
     */

    public static void signUpByPhone(String phone,
                                     String password,
                                     String avatar,
                                     UIListenerInterface<String> uiListener){
        String reqUrl="user/register";
        HashMap<String,String> params=new HashMap<>();
        params.put("phone",phone);
        params.put("password", Utils.toMD5(password));
        params.put("avatar",avatar);
        JSONHandler jsonHandler =new JSONHandler(uiListener,new LoginByTelResponse());
        VolleyService.requestJsonByPOST(reqUrl, params, jsonHandler, jsonHandler);

    }


    /**
     * @TODO 捡飞机 POST
     * @param uiListener
     */
    public static void pickPlane(UIListenerInterface<List<PaperPlane>> uiListener){
        String reqUrl="plane/occupy";
        HashMap<String,String> params=new HashMap<>();
        params.put("story_id", "");
        JSONHandler jsonHandler =new JSONHandler(uiListener,new HotPlaneResponse());

        VolleyService.requestJsonByPOST(reqUrl, params, jsonHandler, jsonHandler);
    }
    /**
     * @TODO 获取热门飞机 GET
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
        String reqUrl="plane/hot?amount=%s&offset=%s";
        reqUrl=String.format(reqUrl, amount == null ? "" : amount.toString(), offset == null ? "" : offset.toString());
        Log.e("TAG", reqUrl);
        JSONHandler jsonHandler =new JSONHandler(uiListener,new HotPlaneResponse());


        VolleyService.requestJsonByGET(reqUrl,null, jsonHandler, jsonHandler);

    }

    /**
     * @TODO  获取文章段落 请求飞机段落  GET
     * @param story_id 所在故事id
     * @param amount 每页数量
     * @param offset 页数
     * @param uiListenerInterface List<PaperPlaneDetail>
     *
     */
    public static void pickPlaneDetail(String story_id,
                                       Integer amount,
                                       Integer offset,
                                       UIListenerInterface<List<PaperPlaneDetail>> uiListenerInterface){
        //start
        String reqUrl="paragraph/story-paragraphs?story_id=%s&amount=%s&offset=%s";

        if (amount==null){
            amount=0;
        }
        if (offset == null){
            offset=0;
        }

        if (story_id.equals(0))
            return;
        reqUrl=String.format(reqUrl, story_id==null?"":story_id, amount.toString(), offset.toString());
        Response.Listener<JSONObject> responseListener=
                new JSONHandler(uiListenerInterface,new PickPaperPlaneDetailResponse());
        VolleyService.requestJsonByGET(reqUrl, null, responseListener, null);
    }

    /**
     * @TODO 仍回飞机 GET
     * @param storyid
     * @param uiListener
     */

    public static void throwBackPlane(String storyid,UIListenerInterface uiListener){
        String reqUrl="plane/throw?story_id=%s";
        reqUrl=String.format(reqUrl, storyid);
       JSONHandler jsonHandler=
                new JSONHandler(uiListener,new StringParse());
        VolleyService.requestJsonByGET(reqUrl, null, jsonHandler, jsonHandler);


    }

    /**
     * @TODO  放飞飞机  发送段落 POST
     * @info  这里全部参数都不能用null 为空用""表示！！
     * storyid 为空时，就是叠飞机的操作
     * storyid 不为空是，就是续航的操作
     *
     *
     * @param storyid 所在飞机的id
     * @param title 标题
     * @param content 文章内容内容
     * @param uiListener
     *
     */
    public static void flyPlane(String storyid,
                                      String title,
                                      String content,
                                      UIListenerInterface<String> uiListener){
        //start
        String reqUrl="plane/fly";
        Map<String,String> params=new HashMap<String, String>();
        try {
            params.put("story_id", storyid == null ? "" : storyid);
            params.put("title", title == null ? "" : title);
            params.put("content", content == null ? "" :content);
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONHandler jsonHandler=
                new JSONHandler(uiListener,new StringParse());
        VolleyService.requestJsonByPOST(reqUrl, params,jsonHandler , jsonHandler);
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
