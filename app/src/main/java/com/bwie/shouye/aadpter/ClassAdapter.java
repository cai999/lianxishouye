package com.bwie.shouye.aadpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.shouye.ClassifyActivity;
import com.bwie.shouye.R;
import com.bwie.shouye.bean.ClassBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ll on 2018/8/20.
 */

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private Context context;
    private ClassBean classBean;
  //private List<String> list;
    private OnItemClickListener onItemClickListener;

    public ClassAdapter(Context context, ClassBean classBean) {
        this.context = context;
        this.classBean = classBean;
      /*  list = new ArrayList<>();
        for (ClassBean.classBean bean : classBean.data) {
            list.add(bean.name);
        }*/
    }

    @Override
    public ClassAdapter.ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.classify_layout, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassAdapter.ClassViewHolder holder, final int position) {
             holder.class_name.setText(classBean.data.get(position).name);
              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if(onItemClickListener != null){
                          onItemClickListener.onItemClick(position);
                      }
                  }
              });
    }

    @Override
    public int getItemCount() {
        return classBean.data.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder{
        private TextView class_name;

        public ClassViewHolder(View itemView) {
            super(itemView);
            class_name = (TextView) itemView.findViewById(R.id.class_name);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
