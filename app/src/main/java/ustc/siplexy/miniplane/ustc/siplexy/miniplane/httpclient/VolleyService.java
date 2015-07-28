package ustc.siplexy.miniplane.ustc.siplexy.miniplane.httpclient;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONObject;

import java.util.Map;

/**
 * Created by œË on 2015/7/27.
 */
public class VolleyService {

    private static final String URL="http://www.baidu.com/";

    private static RequestQueue mQueue=null;

    public static void initVolley(Context context){
        mQueue=Volley.newRequestQueue(context);
    }

    public static void requestStringByGET(String requestUrl,
                                          Response.Listener<String> responseListenr,
                                          Response.ErrorListener errorListener){
        if (isQueueEmpty()){
            return ;
        }
        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                requestUrl,responseListenr,errorListener);
        mQueue.add(stringRequest);
        return ;
    }
    public static void requestJsonByGET(String requestUrl,
                                        Map<String,String> params,
                                        Response.Listener<JSONObject> responseListenr,
                                        Response.ErrorListener errorListener){
        if (isQueueEmpty()){
            return ;
        }
        JSONObject jsonObject=new JSONObject(params);

        JsonRequest<JSONObject> jsonRequest =
                new JsonObjectRequest(Request.Method.GET,requestUrl,jsonObject,responseListenr,errorListener);

        mQueue.add(jsonRequest);
        return ;
    }

    public static void requestJsonByPOST(String requestUrl,
                                         Map<String,String> params,
                                         Response.Listener<JSONObject> responseListenr,
                                         Response.ErrorListener errorListener){

        //mQueue
        if (isQueueEmpty()){
            return ;
        }
        JSONObject jsonObject=new JSONObject(params);

        JsonRequest<JSONObject> jsonRequest =
                new JsonObjectRequest(Request.Method.POST,requestUrl,jsonObject,responseListenr,errorListener);

        mQueue.add(jsonRequest);
    }

    private static boolean isQueueEmpty(){
        if (mQueue==null){
            return true;
        }
        else{
            return false;
        }
    }

    /*
       @TODO: ªÒ»°Õº∆¨
    */
    public static void getImg(String url,ImageView view,int defaultImage,int faultImage){
        if (isQueueEmpty()){
            return;
        }
        ImageLoader imageLoader = new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
            }

            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }
        });
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(view,
                defaultImage, faultImage);
        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);
    }
}
