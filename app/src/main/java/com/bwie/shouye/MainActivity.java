package com.bwie.shouye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.bwie.shouye.aadpter.ShouAdapter;
import com.bwie.shouye.api.Api;
import com.bwie.shouye.bean.ShouBean;
import com.bwie.shouye.utils.OkHttpUtils;
import com.bwie.shouye.utils.RequestCollBack;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView xrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {

        OkHttpUtils.getInstance().postData(Api.SHOU_URL, new HashMap<String, String>(), new RequestCollBack() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(response.code()==200){
                    try {
                        String s = response.body().string();
                        //Log.i("aaa","aaa"+s);
                        if(!TextUtils.isEmpty(s))
                            preajson(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private void preajson(String s) {
        //Log.i("ccc","aaa");
        Gson gson = new Gson();
        final ShouBean shouBean = gson.fromJson(s, ShouBean.class);
        if(shouBean!=null){
           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   ShouAdapter shouAdapter = new ShouAdapter(MainActivity.this, shouBean);
                   xrv.setAdapter(shouAdapter);
               }
           });
        }
    }

    private void initView() {
        xrv = (XRecyclerView) findViewById(R.id.xrv);
        xrv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public void liu(View view) {
        startActivity(new Intent(MainActivity.this,LiuActivity.class));
    }
}
