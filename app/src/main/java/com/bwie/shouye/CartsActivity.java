package com.bwie.shouye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.shouye.aadpter.CartsAdapter;
import com.bwie.shouye.aadpter.ShuaXinAll;
import com.bwie.shouye.bean.CartsBean;
import com.bwie.shouye.utils.OkHttpUtils;
import com.bwie.shouye.utils.RequestCollBack;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class CartsActivity extends AppCompatActivity implements ShuaXinAll {

    private Button carts_balance;
    private CheckBox carts_checkbox;
    private TextView carts_price;
    private ImageView carts_retuen;
    private XRecyclerView carts_xrv;
    private List<CartsBean.DataBean> data;
    private CartsAdapter cartsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carts);
        initView();
        initData();
    }

    private void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", "71");
        OkHttpUtils.getInstance().postData("https://www.zhaoapi.cn/product/getCarts", params, new RequestCollBack() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (200 == response.code()) {
                    String string = response.body().string();
                    praseCarts(string);
                }
            }
        });
    }

    private void praseCarts(String string) {
        CartsBean cartsBean = new Gson().fromJson(string, CartsBean.class);
        data = cartsBean.getData();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cartsAdapter = new CartsAdapter(CartsActivity.this, data);
                carts_xrv.setAdapter(cartsAdapter);
                cartsAdapter.setShuaXinAll(CartsActivity.this);
            }
        });
    }

    private void initView() {
        data = new ArrayList<>();
        carts_balance = (Button) findViewById(R.id.carts_balance);
        carts_checkbox = (CheckBox) findViewById(R.id.carts_checkbox);
        carts_price = (TextView) findViewById(R.id.carts_price);
        carts_retuen = (ImageView) findViewById(R.id.carts_retuen);
        carts_xrv = (XRecyclerView) findViewById(R.id.carts_xrv);
        carts_xrv.setLayoutManager(new LinearLayoutManager(CartsActivity.this));
        carts_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carts_checkbox.isChecked()) {
                    if (data != null && data.size() > 0) {
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setIschecked(true);
                            for (int i1 = 0; i1 < data.get(i).getList().size(); i1++) {
                                data.get(i).getList().get(i1).setIscheck(true);
                            }
                        }
                    }
                } else {
                    if (data != null && data.size() > 0) {
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setIschecked(false);
                            Log.i("aaa", data.get(i).ischecked() + "");
                            for (int i1 = 0; i1 < data.get(i).getList().size(); i1++) {
                                data.get(i).getList().get(i1).setIscheck(false);
                            }
                        }
                    }
                }
                cartsAdapter.notifyDataSetChanged();
                jisuanzongjia();
            }
        });
    }

    @Override
    public void Allshua() {
        StringBuilder stringBuilder = new StringBuilder();
        if (cartsAdapter != null) {
            for (int i = 0; i < cartsAdapter.getCartList().size(); i++) {

                stringBuilder.append(cartsAdapter.getCartList().get(i).ischecked());

                for (int i1 = 0; i1 < cartsAdapter.getCartList().get(i).getList().size(); i1++) {

                    stringBuilder.append(cartsAdapter.getCartList().get(i).getList().get(i1).ischeck());
                }
            }
        }
        if (stringBuilder.toString().contains("false")) {
            carts_checkbox.setChecked(false);
        } else {
            carts_checkbox.setChecked(true);
        }
        jisuanzongjia();
    }

    //计算总价
    private void jisuanzongjia() {
        double zongjia = 0;
        for (int i = 0; i < cartsAdapter.getCartList().size(); i++) {
            for (int i1 = 0; i1 < cartsAdapter.getCartList().get(i).getList().size(); i1++) {
                if (cartsAdapter.getCartList().get(i).getList().get(i1).ischeck()) {
                    CartsBean.DataBean.ListBean listBean = cartsAdapter.getCartList().get(i).getList().get(i1);
                    zongjia += Double.parseDouble(listBean.getBargainPrice())*listBean.getEdnum();
                }
            }
            carts_price.setText("总价:￥"+zongjia);
        }

    }
}
