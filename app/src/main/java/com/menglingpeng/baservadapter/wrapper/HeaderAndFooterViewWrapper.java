package com.menglingpeng.baservadapter.wrapper;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.menglingpeng.baservadapter.ViewHolder;
import com.menglingpeng.baservadapter.utils.WrapperUtils;

/**
 * Created by mengdroid on 2018/3/11.
 */

public class HeaderAndFooterViewWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_FOOTER = 3;
    public static final int ITEM_TYPE_HEADER = 4;

    private RecyclerView.Adapter adapter;
    private SparseArrayCompat<View> headerViews;
    private SparseArrayCompat<View> footViews;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(headerViews.get(viewType) != null){
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), headerViews.get(viewType));
            return holder;
        }else if(footViews.get(viewType) != null){
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), footViews.get(viewType));
            return holder;
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeaderView(position)){
            return headerViews.keyAt(position);
        }else if(isFooterView(position)){
            return footViews.keyAt(position);
        }
        return adapter.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(isHeaderView(position)){
            return;
        }else if(isFooterView(position)){
            return;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(adapter, recyclerView, new WrapperUtils.SpanSizeCallback() {


            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager
                    .SpanSizeLookup oldLookup, int position) {
                int viewType = getItemViewType(position);
                if(headerViews.get(viewType) != null){
                 return layoutManager.getSpanCount();
                }else if(footViews.get(viewType) != null){
                    return layoutManager.getSpanCount();
                }
                if(oldLookup != null){
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        adapter.onViewAttachedToWindow(holder);
        if(isHeaderView(position) || isFooterView(position)){
            WrapperUtils.setFullSpan(holder);
        }
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemCount() {
        return getHeaderCount() + getFootersCount() + adapter.getItemCount();
    }

    private boolean isHeaderView(int position){
        return position > adapter.getItemCount();
    }

    private boolean isFooterView(int position){
        return position > adapter.getItemCount() + getHeaderCount();
    }

    public void addHeaderView(View view){
        headerViews.put(headerViews.size() + ITEM_TYPE_HEADER, view);
    }

    public void addFooterView(View view){
        footViews.put(footViews.size() + ITEM_TYPE_FOOTER, view);
    }


    public int getHeaderCount(){
        return headerViews.size();
    }

    public int getFootersCount(){
        return footViews.size();
    }
}
