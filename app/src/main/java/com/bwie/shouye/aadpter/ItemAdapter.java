package com.bwie.shouye.aadpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.shouye.ClassifyActivity;
import com.bwie.shouye.R;
import com.bwie.shouye.ShowActivity;
import com.bwie.shouye.bean.ItemBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ll on 2018/8/20.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private Context context;
    private List<ItemBean.DataBean> data;
    private  ArrayList<String> list;

    public ItemAdapter(Context context, List<ItemBean.DataBean> data) {
        this.context = context;
        this.data = data;
        list = new ArrayList<>();
       for (int i = 0; i <data.size() ; i++) {

          list.add(data.get(i).getName());

       }
    }

    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemAdapter.ItemViewHolder holder, final int position) {
     holder.item_item_tv.setText(list.get(position));
        item_item_Adapter item_item_adapter = new item_item_Adapter(context, data.get(position).getList());
        holder.item_item_xrv.setAdapter(item_item_adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView item_item_tv;
        private XRecyclerView item_item_xrv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            item_item_tv = (TextView) itemView.findViewById(R.id.item_item_tv);
            item_item_xrv = (XRecyclerView) itemView.findViewById(R.id.item_item_xrv);
            item_item_xrv.setLayoutManager(new GridLayoutManager(context,3));
        }
    }
}
