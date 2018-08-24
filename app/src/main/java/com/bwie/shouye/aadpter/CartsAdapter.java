package com.bwie.shouye.aadpter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.shouye.CartsActivity;
import com.bwie.shouye.R;
import com.bwie.shouye.bean.CartsBean;
import java.util.List;

/**
 * Created by ll on 2018/8/21.
 */

public class CartsAdapter extends RecyclerView.Adapter<CartsAdapter.CartsViewHolder> implements ShuaXin{
    private Context context;
    private List<CartsBean.DataBean> cartlist;
    private ShuaXinAll shuaXinAll;

    public CartsAdapter(Context context, List<CartsBean.DataBean> list) {
        this.context = context;
        cartlist = list;
    }
    @Override
    public CartsAdapter.CartsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carts_layout, parent, false);
        return new CartsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartsAdapter.CartsViewHolder holder, int position) {
        final CartsBean.DataBean bean =cartlist.get(position);
        holder.cart_text.setText(bean.getSellerName());
        holder.checkbox.setChecked(bean.ischecked());
        holder.carts_item_xrv.setLayoutManager(new LinearLayoutManager(context));
        Carts_item_Adapter carts_item_adapter = new Carts_item_Adapter(context, bean.getList());
        holder.carts_item_xrv.setAdapter(carts_item_adapter);
        carts_item_adapter.setShuaXin(this);
        for (int i = 0; i < bean.getList().size(); i++) {
            if(!bean.getList().get(i).ischeck()) {
                holder.checkbox.setChecked(false);
                break;
            }else{
               holder.checkbox.setChecked(true);
            }
        }
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkbox.isChecked()){
                    bean.setIschecked(true);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setIscheck(true);
                    }
                }else{
                    bean.setIschecked(false);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setIscheck(false);
                    }
                }
                notifyDataSetChanged();
                if(shuaXinAll!=null) {
                    shuaXinAll.Allshua();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return cartlist.size();
    }
    @Override
    public void shua() {
        notifyDataSetChanged();
        if(shuaXinAll!=null) {
            shuaXinAll.Allshua();
        }
    }
    //暴露修改后的数据
    public List<CartsBean.DataBean> getCartList() {
        return cartlist;
    }

    public void setShuaXinAll(ShuaXinAll shuaXinAll) {
        this.shuaXinAll = shuaXinAll;
    }

    public class CartsViewHolder extends RecyclerView.ViewHolder {
        private TextView cart_text;
        private RecyclerView carts_item_xrv;
        private  CheckBox checkbox;

        public CartsViewHolder(View itemView) {
            super(itemView);
            carts_item_xrv = (RecyclerView) itemView.findViewById(R.id.carts_item_xrv);
            cart_text = (TextView) itemView.findViewById(R.id.cart_text);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }
}
