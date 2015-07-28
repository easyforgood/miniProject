package ustc.siplexy.miniplane;

import android.content.DialogInterface;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import ustc.siplexy.miniplane.api.PlaneApi;
import ustc.siplexy.miniplane.models.PaperPlaneDetail;
import ustc.siplexy.miniplane.ustc.siplexy.miniplane.httpclient.UIListenerInterface;
import ustc.siplexy.miniplane.ustc.siplexy.miniplane.httpclient.VolleyService;


public class MainActivity extends ActionBarActivity implements UIListenerInterface<String>, View.OnClickListener {
    private static final String URL = "http://www.baidu.com/";

    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testBtn = (Button) this.findViewById(R.id.testBtn);
        VolleyService.initVolley(getApplicationContext());

        txtView = (TextView) findViewById(R.id.testTxt);

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
    public void onSuccess(String datas) {

        txtView.setText(datas);
    }

    @Override
    public void onClick(View v) {
        PlaneApi.testApi(null, this);
        ImageView imgView = (ImageView) findViewById(R.id.testImg);
        VolleyService.getImg("http://avatar.csdn.net/4/F/9/1_gaolu.jpg", imgView,
                R.drawable.abc_textfield_default_mtrl_alpha,
                R.drawable.abc_btn_check_to_on_mtrl_015);

    }
}
