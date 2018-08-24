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
import com.bwie.shouye.ShowActivity;
import com.bwie.shouye.bean.ItemBean;
import com.bwie.shouye.bean.ShowBean;

import java.util.List;

/**
 * Created by ll on 2018/8/21.
 */

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder> {
    private Context context;
    private List<ShowBean.DataBean> data;

    public ShowAdapter(Context context, List<ShowBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_layout, parent, false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        holder.show_text.setText(data.get(position).getTitle());
        Glide.with(context).load(data.get(position).getImages().split("\\|")[0]).into(holder.show_iv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ShowViewHolder extends RecyclerView.ViewHolder {

        private ImageView show_iv;
        private TextView show_text;

        public ShowViewHolder(View itemView) {
            super(itemView);
            show_text = (TextView) itemView.findViewById(R.id.show_text);
            show_iv = (ImageView) itemView.findViewById(R.id.show_iv);
        }
    }
}
