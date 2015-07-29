package ustc.siplexy.miniplane;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.List;

import ustc.siplexy.miniplane.api.API;
import ustc.siplexy.miniplane.models.PaperPlane;
import ustc.siplexy.miniplane.api.httpclient.UIListenerInterface;
import ustc.siplexy.miniplane.api.httpclient.VolleyService;


public class MainActivity extends ActionBarActivity implements UIListenerInterface<List<PaperPlane>>, View.OnClickListener {
    private static final String URL = "http://www.baidu.com/";

    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        Button testBtn = (Button) this.findViewById(R.id.testBtn);

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
    public void onClick(View v) {
        API.pickHotPlane(0, 0, this);
        ImageView imgView = (ImageView) findViewById(R.id.testImg);
        API.getImg("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", imgView,
                R.drawable.abc_textfield_default_mtrl_alpha,
                R.drawable.abc_btn_check_to_on_mtrl_015);

    }

    @Override
    public void onSuccess(List<PaperPlane> datas) {
        StringBuffer s=new StringBuffer()
                .append(datas.get(0).getTotal_favours())
                .append(datas.get(1).getTotal_favours())
                .append(datas.get(2).getTotal_favours());
        txtView.setText(s.toString());
    }

    @Override
    public void onError(VolleyError error) {

    }

}
