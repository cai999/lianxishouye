package com.bwie.shouye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.shouye.aadpter.ShowAdapter;
import com.bwie.shouye.bean.ShowBean;
import com.bwie.shouye.utils.OkHttpUtils;
import com.bwie.shouye.utils.RequestCollBack;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ShowActivity extends AppCompatActivity {

    private TextView show_name;
    private XRecyclerView show_xrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        initData();
    }

    private void initData() {
        String pscid1 = getIntent().getStringExtra("pscid");
        Log.i("ddd",pscid1);
        HashMap<String,String> prams = new HashMap<>();
        prams.put("pscid",pscid1);
        OkHttpUtils.getInstance().postData("https://www.zhaoapi.cn/product/getProducts", prams, new RequestCollBack() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(ShowActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              if(200==response.code()){
                  String string = response.body().string();
                  praseShow(string);
              }
            }
        });
    }

    private void praseShow(String string) {
        ShowBean showBean = new Gson().fromJson(string, ShowBean.class);
        final List<ShowBean.DataBean> data = showBean.getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ShowAdapter showAdapter = new ShowAdapter(ShowActivity.this, data);
                show_xrv.setAdapter(showAdapter);
            }
        });
    }

    private void initView() {
        show_name = (TextView) findViewById(R.id.show_name);
        show_xrv = (XRecyclerView) findViewById(R.id.show_xrv);
        show_xrv.setLayoutManager(new LinearLayoutManager(ShowActivity.this));
    }
}
