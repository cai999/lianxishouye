package com.bwie.shouye.aadpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.shouye.Adder;
import com.bwie.shouye.R;
import com.bwie.shouye.bean.CartsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by ll on 2018/8/21.
 */

class Carts_item_Adapter extends RecyclerView.Adapter<Carts_item_Adapter.Cart_Item_ViewHolder> {
    private Context context;
    private List<CartsBean.DataBean.ListBean> list;
    private ShuaXin shuaXin;
    private ShuaXinAll shuaXinAll;

    public void setShuaXin(ShuaXin shuaXin) {
        this.shuaXin = shuaXin;
    }

    public void setShuaXinAll(ShuaXinAll shuaXinAll) {
        this.shuaXinAll = shuaXinAll;
    }

    public Carts_item_Adapter(Context context, List<CartsBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public Carts_item_Adapter.Cart_Item_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new Cart_Item_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Carts_item_Adapter.Cart_Item_ViewHolder holder, int position) {
        final CartsBean.DataBean.ListBean bean = list.get(position);
        holder.cart_intem_text.setText(bean.getTitle());
        //Log.i("ccc",list.get(position).getTitle());
        String[] split = bean.getImages().split("\\|");
        if (split.length != 0) {
            Glide.with(context).load(split[0]).into(holder.cart_intem_iv);
        }
        holder.check.setChecked(bean.ischeck());
        holder.carts_getBargainprice.setText(bean.getBargainPrice() + "");
        //Log.i("bbbb","价格为"+list.get(position).getBargainPrice()+"");
        holder.carts_price.setText(bean.getPrice() + "");
        holder.jiajianqi.getnumed(bean.getEdnum());
        holder.jiajianqi.setNumListener(new Adder.NumListener() {
            @Override
            public void getNum(int num) {
               bean.setEdnum(num);
                if(shuaXin!=null){
                    shuaXin.shua();
                }
            }
        });
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.check.isChecked()) {
                    bean.setIscheck(true);
                } else {
                    bean.setIscheck(false);
                }
                if (shuaXin != null) {
                    shuaXin.shua();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Cart_Item_ViewHolder extends RecyclerView.ViewHolder {

        private Adder jiajianqi;
        private CheckBox check;
        private TextView carts_getBargainprice;
        private TextView cart_intem_text, carts_price;
        private ImageView cart_intem_iv;

        public Cart_Item_ViewHolder(View itemView) {
            super(itemView);
            cart_intem_iv = (ImageView) itemView.findViewById(R.id.cart_intem_iv);
            check = (CheckBox) itemView.findViewById(R.id.check);
            cart_intem_text = (TextView) itemView.findViewById(R.id.cart_intem_text);
            carts_price = (TextView) itemView.findViewById(R.id.carts_price);
            carts_getBargainprice = (TextView) itemView.findViewById(R.id.carts_getBargainprice);
            jiajianqi = (Adder) itemView.findViewById(R.id.jiajianqi);
        }
    }
}
