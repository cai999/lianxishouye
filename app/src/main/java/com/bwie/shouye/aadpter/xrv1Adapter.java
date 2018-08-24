package com.bwie.shouye.aadpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.shouye.R;
import com.bwie.shouye.bean.ShouBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ll on 2018/8/17.
 */

class xrv1Adapter extends RecyclerView.Adapter<xrv1Adapter.xrv1Holder>{
    private Context context;
    private ShouBean shouBean;
    private List<String> list;

    public xrv1Adapter(Context context, ShouBean shouBean) {
        this.context = context;
        this.shouBean = shouBean;
        list = new ArrayList<>();
        for (ShouBean.Home.Fenlei fenlei : shouBean.data.fenlei) {
            list.add(fenlei.icon);
        }

    }

   @Override
    public xrv1Adapter.xrv1Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("aaa", "11111");
        View view = LayoutInflater.from(context).inflate(R.layout.class_layout, parent, false);
        return new xrv1Holder(view);
    }

    @Override
    public void onBindViewHolder(xrv1Holder holder, int position) {
        holder.tv.setText(shouBean.data.fenlei.get(position).name);
       // for (int i = 0; i < list.size(); i++) {
            Glide.with(context).load(list.get(position))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.img);
        //}
             /*Glide.with(context).load(list.get(position))
                     .placeholder(R.mipmap.ic_launcher)
                     .into(((Holder) holder).img);*/
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

     class xrv1Holder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv;

        public xrv1Holder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

   /* public static class Holder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv;
        public Holder(View view) {
            super(view);
             img = (ImageView) view.findViewById(R.id.img);
             tv = (TextView) view.findViewById(R.id.tv);
        }
    }*/
}
