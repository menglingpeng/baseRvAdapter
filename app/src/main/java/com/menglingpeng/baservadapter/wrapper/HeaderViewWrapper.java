package com.menglingpeng.baservadapter.wrapper;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.menglingpeng.baservadapter.ViewHolder;

/**
 * Created by mengdroid on 2018/3/10.
 */

public class HeaderViewWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_HEADER = 4;

    private RecyclerView.Adapter adapter;
    private SparseArrayCompat<View> headerViews;

    public HeaderViewWrapper(RecyclerView.Adapter adapter){
        this.adapter = adapter;
        headerViews = new SparseArrayCompat<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(headerViews.get(viewType) != null){
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), headerViews.get(viewType));
            return holder;
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeaderView(position)){
            return headerViews.keyAt(position);
        }
        return adapter.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(isHeaderView(position)){
            return;
        }
        adapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return getItemCount() + headerViews.size();
    }

    private boolean isHeaderView(int position){
        return position > adapter.getItemCount();
    }

    public void addHeaderView(View view){
        headerViews.put(headerViews.size() + ITEM_TYPE_HEADER, view);
    }

    public int getHeaderCount(){
        return headerViews.size();
    }
}
