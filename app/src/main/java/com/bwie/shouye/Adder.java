package com.bwie.shouye;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by ll on 2018/8/23.
 */

public class Adder extends LinearLayout {

    private Button adder_jia;
    private Button adder_jian;
    private EditText adder_tv;
    private int num =1;
    private NumListener numListener;

    public Adder(Context context) {
        this(context,null);
    }

    public Adder(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Adder(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
//设置自定义布局
    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.adderlayout, this, true);
         adder_jia = (Button) view.findViewById(R.id.adder_jia);
         adder_tv = (EditText) view.findViewById(R.id.adder_tv);
         adder_jian = (Button) view.findViewById(R.id.adder_jian);

        adder_tv.setText(num+"");
        adder_jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                adder_tv.setText(num+"");
                if(numListener!=null){
                    numListener.getNum(num);
                }
            }
        });
        adder_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if(num<=0){
                    Toast.makeText(getContext(),"数量不能小于0",Toast.LENGTH_SHORT).show();
                    num=1;
                }
                adder_tv.setText(num+"");
                if(numListener!=null){
                    numListener.getNum(num);
                }
            }
        });
    }

    public void getnumed(int n){
        adder_tv.setText(n+"");
        num = Integer.parseInt(adder_tv.getText().toString());
    }
    public void setNumListener(NumListener numListener) {
        this.numListener = numListener;
    }

    public interface NumListener{
        void getNum(int num);
    }

}
