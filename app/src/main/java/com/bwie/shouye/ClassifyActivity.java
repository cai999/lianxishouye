package com.bwie.shouye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.shouye.aadpter.ClassAdapter;
import com.bwie.shouye.aadpter.ItemAdapter;
import com.bwie.shouye.bean.ClassBean;
import com.bwie.shouye.bean.ItemBean;
import com.bwie.shouye.utils.OkHttpUtils;
import com.bwie.shouye.utils.RequestCollBack;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ClassifyActivity extends AppCompatActivity {

    private XRecyclerView zhu_xrv;
    private XRecyclerView item_xrv;
    private TextView item_tv;
    private int id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify);
        initView();
        initDataZhu();
        initdataItem();

    }

    private void initdataItem() {
       HashMap<String, String> prams = new HashMap<>();
        prams.put("cid",id+"");
        OkHttpUtils.getInstance().postData("https://www.zhaoapi.cn/product/getProductCatagory",prams, new RequestCollBack() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(ClassifyActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(200==response.code()){
                    String string = response.body().string();
                    pread(string);
                }
            }
        });
    }

    private void pread(String string) {
        Gson gson = new Gson();
        ItemBean itemBean = gson.fromJson(string, ItemBean.class);
        final List<ItemBean.DataBean> data = itemBean.getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ItemAdapter itemAdapter = new ItemAdapter(ClassifyActivity.this, data);
                item_xrv.setAdapter(itemAdapter);
            }
        });

    }

    private void initDataZhu() {
        OkHttpUtils.getInstance().postData("https://www.zhaoapi.cn/product/getCatagory", new HashMap<String, String>(), new RequestCollBack() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(ClassifyActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(200==response.code()){
                    String string = response.body().string();
                    preajson(string);
                }
            }
        });
    }

    private void preajson(String string) {
        Gson gson = new Gson();
        final ClassBean classBean = gson.fromJson(string, ClassBean.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ClassAdapter classAdapter = new ClassAdapter(ClassifyActivity.this, classBean);
                zhu_xrv.setAdapter(classAdapter);
                classAdapter.setOnItemClickListener(new ClassAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        String name = classBean.data.get(position).name;
                        item_tv.setText(name);
                        id = classBean.data.get(position).cid;
                        initdataItem();
                    }
                });
            }
        });
    }

    private void initView() {
        zhu_xrv = (XRecyclerView) findViewById(R.id.zhu_xrv);
        zhu_xrv.setLayoutManager(new LinearLayoutManager(ClassifyActivity.this));
        item_xrv = (XRecyclerView) findViewById(R.id.item_xrv);
        item_xrv.setLayoutManager(new LinearLayoutManager(ClassifyActivity.this));
        item_tv = (TextView) findViewById(R.id.item_tv);
    }
}
