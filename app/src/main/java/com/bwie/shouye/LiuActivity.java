package com.bwie.shouye;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.shouye.utils.OkHttpUtils;
import com.bwie.shouye.utils.RequestCollBack;
import com.fynn.fluidlayout.FluidLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class LiuActivity extends AppCompatActivity {
    private EditText edt;
    private Button but;
    private LayoutInflater layoutInflater;
    private TextView tvAttrTag;
    private List<String> list2 = new ArrayList<>();
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liu);
        initView();
    }
    private void initView() {
        edt = (EditText) findViewById(R.id.liu_ed);
        but = (Button) findViewById(R.id.btn);
        myView = (MyView) findViewById(R.id.myView);
        layoutInflater = LayoutInflater.from(this);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edt.getText().toString();
                View item = layoutInflater.inflate(R.layout.liu_layout, null);
                tvAttrTag = (TextView) item.findViewById(R.id.liu_text);
                list2.add(s);
                for (int i = 0; i < list2.size(); i++) {
                    tvAttrTag.setText(list2.get(i));
                }
                myView.addView(item);
            }
        });
    }

}
