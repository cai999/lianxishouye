package com.bwie.shouye;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.bwie.shouye.aadpter.SousuoAdapter;
import com.bwie.shouye.bean.SousuoBean;
import com.bwie.shouye.utils.OkHttpUtils;
import com.bwie.shouye.utils.RequestCollBack;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.bwie.shouye.R.id.liu_ed;

public class SousuoActivity extends AppCompatActivity {

    private XRecyclerView sousuo_xrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousuo);
        initView();
        initData();
    }

    private void initData() {
        String s = getIntent().getStringExtra("keywords");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("keywords", s);
        OkHttpUtils.getInstance().postData("https://www.zhaoapi.cn/product/searchProducts", hashMap, new RequestCollBack() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (200 == response.code()) {
                            String string = response.body().string();
                            if (!TextUtils.isEmpty(string)) {
                                praseliu(string);
                            }
                        }
                    }
                });
    }

    private void praseliu(String string) {
        Gson gson = new Gson();
        final SousuoBean sousuoBean = gson.fromJson(string, SousuoBean.class);
        final List<SousuoBean.DataBean> data = sousuoBean.getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SousuoAdapter sousuoAdapter = new SousuoAdapter(SousuoActivity.this, data);
                sousuo_xrv.setAdapter(sousuoAdapter);
            }
        });

    }

    private void initView() {
        sousuo_xrv = (XRecyclerView) findViewById(R.id.sousuo_xrv);
        sousuo_xrv.setLayoutManager(new LinearLayoutManager(SousuoActivity.this));
    }
}
