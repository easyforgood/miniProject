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

    private static final String API_URL="http://104.236.124.234:8080/%s";

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

    private static void requestJson(int requestType,
                                    String reqUrl,
                                    Map<String,String> params,
                                    Response.Listener<JSONObject> responseListenr,
                                    Response.ErrorListener errorListener){
        //start
        if (isQueueEmpty()){
            return ;
        }
        JSONObject jsonObject=new JSONObject(params);
        String requestUrl=getAbsoluteApiUrl(reqUrl);
        JsonRequest<JSONObject> jsonRequest =
                new JsonObjectRequest(requestType,requestUrl,jsonObject,responseListenr,errorListener);

        mQueue.add(jsonRequest);
        return ;

    }
    public static void requestJsonByGET(String reqUrl,
                                        Map<String,String> params,
                                        Response.Listener<JSONObject> responseListenr,
                                        Response.ErrorListener errorListener){
        requestJson(Request.Method.GET,reqUrl,params,responseListenr,errorListener);

    }

    public static void requestJsonByPOST(String reqUrl,
                                         Map<String,String> params,
                                         Response.Listener<JSONObject> responseListenr,
                                         Response.ErrorListener errorListener){

        //start
        requestJson(Request.Method.POST, reqUrl, params, responseListenr, errorListener);

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

    public static String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL, partUrl);
        Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }
}
