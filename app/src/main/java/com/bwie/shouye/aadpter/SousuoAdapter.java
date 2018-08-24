package com.bwie.shouye.aadpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.shouye.R;
import com.bwie.shouye.SousuoActivity;
import com.bwie.shouye.bean.ItemBean;
import com.bwie.shouye.bean.SousuoBean;

import java.util.List;

/**
 * Created by ll on 2018/8/22.
 */

public class SousuoAdapter extends RecyclerView.Adapter<SousuoAdapter.SousuoViewHolder>{
    private Context context;
    private List<SousuoBean.DataBean> data;

    public SousuoAdapter(Context context, List<SousuoBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public SousuoAdapter.SousuoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sousuo_layout, parent, false);
        return new SousuoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SousuoAdapter.SousuoViewHolder holder, int position) {
holder.sousuo_price.setText(data.get(position).getBargainPrice());
        holder.sousuo_tv.setText(data.get(position).getTitle());
        Glide.with(context).load(data.get(position).getImages().split("\\|")[0]).into(holder.sousuo_iv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SousuoViewHolder extends RecyclerView.ViewHolder {
        private ImageView sousuo_iv;
        private  TextView sousuo_tv,sousuo_price;


        public SousuoViewHolder(View itemView) {
            super(itemView);
            sousuo_iv = (ImageView) itemView.findViewById(R.id.sousuo_iv);
            sousuo_tv = (TextView) itemView.findViewById(R.id.sousuo_tv);
            sousuo_price = (TextView) itemView.findViewById(R.id.sousuo_price);
        }
    }
}
