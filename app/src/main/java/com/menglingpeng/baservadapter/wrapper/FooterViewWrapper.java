package com.menglingpeng.baservadapter.wrapper;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.menglingpeng.baservadapter.ViewHolder;

import java.util.List;

/**
 * Created by mengdroid on 2018/3/10.
 */

public class FooterViewWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final int TYPE_VIEW_FOOTER = 3;

    private RecyclerView.Adapter adapter;
    private SparseArrayCompat<View> footViews;

    public FooterViewWrapper(RecyclerView.Adapter adapter){
        this.adapter = adapter;
        footViews = new SparseArrayCompat<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(footViews.get(viewType) != null){
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), footViews.get(viewType));
            return holder;
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if(isFooterView(position)){
            return footViews.keyAt(position);
        }
        return adapter.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(isFooterView(position)){
            return;
        }
        adapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return getItemCount() + footViews.size();
    }

    private boolean isFooterView(int position){
        return position > adapter.getItemCount();
    }

    public void addFooterView(View view){
        list.add(list.size() + TYPE_VIEW_FOOTER, view);
    }

    public int getFootersCount(){
        return list.size();
    }
}
