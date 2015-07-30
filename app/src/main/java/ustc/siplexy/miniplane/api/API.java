package ustc.siplexy.miniplane.api;/**
 * Created by �� on 2015/7/28.
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
 * @TODO �ṩ�� ǰ�˵Ľӿ�
 * @Project:
 * @Package:
 * @Description:
 * @author: siplexypeng ����
 * @date:
 * @version: V1.0
 */
public class API {
    public static final String SUCCESS="success";
    public static final String ZERO="0";

    /**
     * @TODO ��½ POST
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
     * @TODO��ע�� POST
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
     * @TODO ��ȡ�ɻ� get
     * @param uiListener
     */
    public static void pickPlane(UIListenerInterface<List<PaperPlane>> uiListener){
        String reqUrl="plane/occupy";
        JSONHandler jsonHandler =new JSONHandler(uiListener,new HotPlaneResponse());
        VolleyService.requestJsonByGET(reqUrl, null, jsonHandler, jsonHandler);
    }
    /**
     * @TODO ��ȡ���ŷɻ� GET
     *
     * @param amount ������Ϣ������
     * @param offset �����ƫ����
     * @param uiListener List<PaperPlane>����
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
     * @TODO  ��ȡ���¶��� ����ɻ�����  GET
     * @param story_id ���ڹ���id
     * @param amount ��������
     * @param offset ƫ����
     * @param uiListenerInterface List<PaperPlaneDetail>
     *
     */
    public static void pickPlaneDetail(Integer story_id,
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
        if (null==story_id){
            story_id=0;
        }
        if (story_id.equals(0))
            return;
        reqUrl=String.format(reqUrl, story_id.toString(), amount.toString(), offset.toString());
        Response.Listener<JSONObject> responseListener=
                new JSONHandler(uiListenerInterface,new PickPaperPlaneDetailResponse());
        VolleyService.requestJsonByGET(reqUrl, null, responseListener, null);
    }

    /**
     * @TODO �Իطɻ� GET
     * @param storyid
     * @param uiListener
     */

    public static void throwBackPlane(String storyid,UIListenerInterface uiListener){
        String reqUrl="plane/throw?story_id=%s";
        reqUrl=String.format(reqUrl, storyid);
       JSONHandler jsonHandler=
                new JSONHandler(uiListener,new StringParse());
        VolleyService.requestJsonByGET(reqUrl,null,jsonHandler,jsonHandler);


    }

    /**
     * @TODO  �ŷɷɻ�  ���Ͷ��� POST
     * @param storyid ���ڷɻ���id
     * @param title ����
     * @param content ������������
     * @param uiListener
     *
     */
    public static void flyPlane(String storyid,
                                      String title,
                                      String content,
                                      UIListenerInterface uiListener){
        //start
        String reqUrl="plane/fly";
        Map<String,String> params=new HashMap<String, String>();

        params.put("story_id",storyid==null?"":storyid);
        params.put("title", title == null ? "" : title);
        params.put("content", content==null? "" : content);
        JSONHandler jsonHandler=
                new JSONHandler(uiListener,new StringParse());
        VolleyService.requestJsonByPOST(reqUrl, params,jsonHandler , jsonHandler);
    }

    /**
     * @TODO �첽����ͼƬ
     * @param reqUrl ����url
     * @param view �󶨵�imageview
     * @param defaultImg Ĭ������µ���Դ�ļ�
     * @param faultImg ��������µ���Դ�ļ�
     */
    public static void getImg(String reqUrl,ImageView view,int defaultImg,int faultImg){

        VolleyService.getImg(reqUrl,view,defaultImg,faultImg);
    }

}
