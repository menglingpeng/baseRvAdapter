package com.menglingpeng.baservadapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.menglingpeng.baservadapter.ViewHolder;

import java.util.List;

/**
 * Created by mengdroid on 2018/3/8.
 */

public class CommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected Context context;
    protected List  datas;

    protected AdapterView.OnItemClickListener listener;

    public CommonAdapter(Context context, List datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.createViewHolder(context, parent, );
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public List getDatas(){
        return datas;
    }

    public interface onItemClickListener{
        void onTtemClick(View view, RecyclerView.ViewHolder holder, int position);
        void onItemLongClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        this.listener = listener;
    }
}
