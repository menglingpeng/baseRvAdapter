package com.menglingpeng.baservadapter.wrapper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.menglingpeng.baservadapter.ViewHolder;

/**
 * Created by mengdroid on 2018/3/7.
 */

public class EmptyViewWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.Adapter adapter;
    private View emptyView;
    private int emptyLayoutID;

    public static final int ITEM_TYPE_EMPTY = 1;

    public EmptyViewWrapper(RecyclerView.Adapter adapter){
        this.adapter = adapter;
    }

    private boolean isEmpty(){
        return (emptyView != null || emptyLayoutID != 0) && adapter.getItemCount() ==0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(isEmpty()){
            ViewHolder holder;
            if(emptyView != null){
                holder = ViewHolder.createViewHolder(parent.getContext(), emptyView);
            }else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, emptyLayoutID);
            }
            return holder;
        };
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if(isEmpty()){
            return ITEM_TYPE_EMPTY;
        }
        return adapter.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isEmpty()){
            return;
        }
        adapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        if(isEmpty()){
            return 1;
        }
        return adapter.getItemCount();
    }
}
