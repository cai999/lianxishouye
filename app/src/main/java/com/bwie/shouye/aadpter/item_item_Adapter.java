package com.bwie.shouye.aadpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.shouye.R;
import com.bwie.shouye.ShowActivity;
import com.bwie.shouye.bean.ItemBean;

import java.util.List;

/**
 * Created by ll on 2018/8/20.
 */

class item_item_Adapter extends RecyclerView.Adapter<item_item_Adapter.item_item_ViewHolder>{
   private Context context;
    private List<ItemBean.DataBean.ListBean> data;
    //private OnItemClickListener onItemClickListener;

    public item_item_Adapter(Context context, List<ItemBean.DataBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public item_item_Adapter.item_item_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_item_layout, parent, false);
        return new item_item_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(item_item_Adapter.item_item_ViewHolder holder, final int position) {
       Glide.with(context).load(data.get(position).getIcon()).into(holder.item_item_iv);
        holder.item_item_text.setText(data.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowActivity.class);
                intent.putExtra("pscid",data.get(position).getPscid()+"");
                //Log.i("fff",data.get(position).getPscid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class item_item_ViewHolder extends RecyclerView.ViewHolder{

        private ImageView item_item_iv;
        private TextView item_item_text;

        public item_item_ViewHolder(View itemView) {
            super(itemView);
            item_item_text = (TextView) itemView.findViewById(R.id.item_item_text);
            item_item_iv = (ImageView) itemView.findViewById(R.id.item_item_iv);
        }
    }
   /* public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(int show_position);
    }*/
}
