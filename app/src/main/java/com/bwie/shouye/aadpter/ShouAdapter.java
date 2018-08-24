package com.bwie.shouye.aadpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.shouye.MainActivity;
import com.bwie.shouye.R;
import com.bwie.shouye.bean.ShouBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ll on 2018/8/16.
 */

public class ShouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ShouBean shouBean;
    private final int TYPE1 = 0;
    private final int TYPE2 = 1;
    private final int TYPE3 = 2;
    private List<String> list;

    public ShouAdapter(Context context, ShouBean shouBean) {
        this.context = context;
        this.shouBean = shouBean;
        //Log.i("aaa", shouBean.data.banner.toString());
        list = new ArrayList<>();
        for (ShouBean.Home.Banner banner : shouBean.data.banner) {
            //Log.i("aaa", banner.icon);
            list.add(banner.icon);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //Log.i("aaa", "aaaa");
        if (position == 0) {
            return TYPE1;
        } else if (position == 1) {
            return TYPE2;
        }
        return TYPE3;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE1) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_1, parent, false);
            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        } else if (viewType == TYPE2) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_2, parent, false);
            return new ClassHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_3, parent, false);
            return new MyHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof BannerHolder) {
            ((BannerHolder) holder).banner.setImages(list).setImageLoader(new bannerGlide()).start();
        } else if (holder instanceof ClassHolder) {
            xrv1Adapter xrv1Adapter = new xrv1Adapter(context,shouBean);
            ((ClassHolder) holder).xrv1.setAdapter(xrv1Adapter);
        } else if (holder instanceof MyHolder) {
            ((MyHolder) holder).text.setText("数据展示");
        }
    }

    class bannerGlide extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class BannerHolder extends ViewHolder {
        Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }
    public  class ClassHolder extends RecyclerView.ViewHolder {
        private XRecyclerView xrv1;

        public ClassHolder(View view) {
            super(view);
            xrv1 = (XRecyclerView) view.findViewById(R.id.xrv1);
            xrv1.setLayoutManager(new GridLayoutManager(context,10));
            xrv1.setLoadingMoreEnabled(true);
            xrv1.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {

                }

                @Override
                public void onLoadMore() {

                }
            });
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private TextView text;

        public MyHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.text);
        }
    }
    }

