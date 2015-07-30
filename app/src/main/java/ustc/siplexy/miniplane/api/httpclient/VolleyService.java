package ustc.siplexy.miniplane.api.httpclient;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 翔 on 2015/7/27.
 */
public class VolleyService {

    private static final String API_URL="http://203.195.223.95:5000/%s";

    private static RequestQueue mQueue=null;
    private static String cookies;

    public static void setCookies(String cookies){
        VolleyService.cookies=cookies;

    }

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
        JSONObject jsonObject=params==null?null:new JSONObject(params);
        String requestUrl=getAbsoluteApiUrl(reqUrl);
        JsonRequest<JSONObject> jsonRequest =
                getJsonRequest(requestType, requestUrl, jsonObject, responseListenr, errorListener);

        if (jsonRequest!=null){
            mQueue.add(jsonRequest);
        }
        return ;

    }
    public static void requestJsonByGET(String reqUrl,
                                        Map<String,String> params,
                                        Response.Listener<JSONObject> responseListenr,
                                        Response.ErrorListener errorListener){
        requestJson(Request.Method.GET, reqUrl, params, responseListenr, errorListener);

    }

    public static void requestJsonByPOST(String reqUrl,
                                         Map<String,String> params,
                                         Response.Listener<JSONObject> responseListenr,
                                         Response.ErrorListener errorListener){

        //start
        requestJson(Request.Method.POST, reqUrl, params, responseListenr, errorListener);

    }

    /**
     * @TODO 获取request对象 这里重写了JsonRequest 为了获取cookies
     * @param requestType
     * @param reqUrl
     * @param params
     * @param responseListenr
     * @param errorListener
     * @return
     */
    public static JsonRequest<JSONObject> getJsonRequest(int requestType,
                                      String reqUrl,
                                      JSONObject params,
                                      Response.Listener<JSONObject> responseListenr,
                                      Response.ErrorListener errorListener){
        //start
        JsonRequest<JSONObject> request=
                new JsonObjectRequest(requestType,reqUrl,
                        params,responseListenr,errorListener){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                String cookies=response.headers.get("Set-Cookie");
                if(cookies != null) {
                    VolleyService.cookies = cookies;
                }
                Log.e("TAG",cookies==null?"nothing":cookies);

                try {
                    String jsonString =
                            new String(response.data, HttpHeaderParser.parseCharset(response.headers));


                    JSONObject jsonObject = new JSONObject(jsonString);

                    Log.w("LOG","jsonObject "+ jsonObject.toString());
                    return Response.success(jsonObject,
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> mHeaders=new HashMap<>();
                if(VolleyService.cookies!=null) {
                    mHeaders.put("Cookie", VolleyService.cookies);
                }

                return mHeaders;
            }

        };
        return request;

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
       @TODO: 获取图片
    */
    public static void getImg(String reqUrl,ImageView view,int defaultImage,int faultImage){
        String responseURL=getAbsoluteApiUrl(reqUrl);
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
        imageLoader.get(responseURL, listener);
    }

    public static String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL, partUrl);
        Log.d("BASE_CLIENT", "request:" + url);
        return url;
    }
}
