package ustc.siplexy.miniplane;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;


import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import com.tencent.connect.UserInfo;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.List;

import com.tencent.connect.common.Constants;

import ustc.siplexy.miniplane.api.API;
import ustc.siplexy.miniplane.cache.ACache;
import ustc.siplexy.miniplane.models.PaperPlane;
import ustc.siplexy.miniplane.api.httpclient.UIListenerInterface;
import ustc.siplexy.miniplane.api.httpclient.VolleyService;
import ustc.siplexy.miniplane.models.PaperPlaneDetail;


public class MainActivity extends ActionBarActivity implements UIListenerInterface<List<PaperPlaneDetail>>, View.OnClickListener, IUiListener, IRequestListener {
    private static final String URL = "http://www.baidu.com/";
    private final String cacheName = "test";


    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button testBtn = (Button) this.findViewById(R.id.testBtn);

        txtView = (TextView) findViewById(R.id.testTxt);
        String cacheValue = MainApplication.getACache().getAsString(cacheName);
        txtView.setText(cacheValue == null ? "Hello Sip" : cacheValue);


        testBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {


        API.loginByPhone("18051114512", "test", new UIListenerInterface<String>() {
            @Override
            public void onSuccess(String datas) {
                Toast.makeText(MainApplication.getInstance(), "Login Success", Toast.LENGTH_LONG).show();
                Log.e("TAG", "login success");
            }

            @Override
            public void onError(VolleyError error) {
                Toast.makeText(MainApplication.getInstance(), "Login erro", Toast.LENGTH_LONG).show();
                Log.e("TAG", "login error");
            }
        });

        API.pickPlaneDetail("55ba28d801766f090352bd24", 1, 1, this);

        API.pickPlaneDetail("55ba28d801766f090352bd24",3,1,this);


        //API.pickHotPlane(1,1,this);

        Tencent mTencent = Tencent.createInstance("1104715967", this.getApplicationContext());
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", this);


        } else {
            mTencent.getAccessToken();
            UserInfo mInfo=new UserInfo(this,mTencent.getQQToken());
            mInfo.getUserInfo(this);
        }
        ImageView imgView = (ImageView) findViewById(R.id.testImg);
        API.getImg("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", imgView,
                R.drawable.abc_textfield_default_mtrl_alpha,
                R.drawable.abc_btn_check_to_on_mtrl_015);


    }


/*

    @Override
    public void onSuccess(String datas) {
        if (datas ==null){
            Log.e("TAG","SORYY EMPTY");
            return ;
        }
        Log.e("TAG",datas);
        txtView.setText(datas);
        MainApplication.getACache().put(cacheName,datas);
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
        return ;
    }
*/


 /*

    @Override
    public void onSuccess(List<PaperPlane> datas) {
        Log.e("TAG", "pickHot now");
        if (datas !=null) {
            MainApplication.getACache().put(cacheName, datas.get(0).getStoryid().toString());
        }
        txtView.setText(datas == null ? "EMpty" : datas.get(0).getTotal_collections().toString());
    }*/

    @Override
    public void onSuccess(List<PaperPlaneDetail> datas) {
        if (datas!=null){
            for (PaperPlaneDetail item: datas){
                String temp=txtView.getText().toString();
                txtView.setText(temp+"\n"+"para id :"+
                        item.getParagraph_id()+"content:"+item.getContent());
            }

        }

    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onComplete(Object o) {
        Toast.makeText(this, o == null ? "null" : o.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onComplete(JSONObject jsonObject) {
        Toast.makeText(this, jsonObject == null ? "null" : jsonObject.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIOException(IOException e) {

    }

    @Override
    public void onMalformedURLException(MalformedURLException e) {

    }

    @Override
    public void onJSONException(JSONException e) {

    }

    @Override
    public void onConnectTimeoutException(ConnectTimeoutException e) {

    }

    @Override
    public void onSocketTimeoutException(SocketTimeoutException e) {

    }

    @Override
    public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException e) {

    }

    @Override
    public void onHttpStatusException(HttpUtils.HttpStatusException e) {

    }

    @Override
    public void onUnknowException(Exception e) {

    }
}
