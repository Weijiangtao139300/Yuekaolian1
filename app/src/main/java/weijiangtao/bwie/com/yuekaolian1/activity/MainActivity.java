package weijiangtao.bwie.com.yuekaolian1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

import weijiangtao.bwie.com.yuekaolian1.R;
import weijiangtao.bwie.com.yuekaolian1.adapter.Myadapter;
import weijiangtao.bwie.com.yuekaolian1.bean.Bean;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    private SpringView spring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spring = (SpringView) findViewById(R.id.spring);
        listview = (ListView) findViewById(R.id.listview);
        spring.setType(SpringView.Type.FOLLOW);
        spring.setHeader(new DefaultHeader(this));
        spring.setFooter(new DefaultFooter(this));

        
        spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                spring.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initdata();
                       //停止刷新
                        spring.onFinishFreshAndLoad();
                    }
                },2000);
                
            }

            @Override
            public void onLoadmore() {


                spring.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spring.onFinishFreshAndLoad();
                    }
                },2000);
            }
        });


        initdata();
    }


        

    private void initdata() {
        String url="http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=17";
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
//创建一个Request
        final Request request = new Request.Builder()
                .url(url)
                .build();
//new call
        Call call = mOkHttpClient.newCall(request);
//请求加入调度
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Request request, IOException e)
            {
            }

            @Override
            public void onResponse(final Response response) throws IOException
            {
                final String htmlStr =  response.body().string();
                
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        
                        Gson gson=new Gson();
                        Bean bean = gson.fromJson(htmlStr, Bean.class);
                        List<Bean.DataBean> data = bean.getData();
                        Myadapter my=new Myadapter(data,MainActivity.this);
                        listview.setAdapter(my);
                        
                    }
                });
            }
        });



    }
}
