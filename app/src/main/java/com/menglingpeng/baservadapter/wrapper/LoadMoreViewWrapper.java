package com.menglingpeng.baservadapter.wrapper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.menglingpeng.baservadapter.ViewHolder;
import com.menglingpeng.baservadapter.utils.WrapperUtils;

/**
 * Created by mengdroid on 2018/3/9.
 */

public class LoadMoreViewWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_LOAD_MORE = 2;

    private RecyclerView.Adapter adapter;
    private View loadMoreView;
    private  int loadMoreLayoutId;
    private OnLoadMoreListener loadMoreListener;

    public LoadMoreViewWrapper(RecyclerView.Adapter adapter){
        this.adapter = adapter;
    }

    private boolean hasLoadMore(){
        return loadMoreView != null || loadMoreLayoutId != 0;
    }

    private boolean isLoadMore(int position){
        return hasLoadMore() && (position >= adapter.getItemCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_LOAD_MORE){
            ViewHolder holder;
            if(loadMoreView != null){
                holder = ViewHolder.createViewHolder(parent.getContext(), loadMoreView);
            }else {
                holder = ViewHolder.createViewHolder(parent.getContext(), parent, loadMoreLayoutId);
            }
        }
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if(isLoadMore(position)){
            return ITEM_TYPE_LOAD_MORE;
        }
        return adapter.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(isLoadMore(position)){
            if(loadMoreListener != null){
                loadMoreListener.onLoadMoreReu();
            }
            return;
        }
        adapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(adapter, recyclerView, new WrapperUtils.SpanSizeCallback() {


            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager
                    .SpanSizeLookup oldLookup, int position) {
                if(isLoadMore(position)){
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
        adapter.onViewAttachedToWindow(holder);
        if(isLoadMore(holder.getLayoutPosition())){
            setFullSpan(holder);
        }
    }

    private void setFullSpan(RecyclerView.ViewHolder holder)
    {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();

        if (layoutParams != null
                && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)
        {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) layoutParams;

            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }

    public LoadMoreViewWrapper setLoadMoreView(View loadMoreView){
        this.loadMoreView = loadMoreView;
        return this
    }

    public LoadMoreViewWrapper setLoadMoreView(int loadMoreLayoutId){
        this.loadMoreLayoutId = loadMoreLayoutId;
        return this
    }

    public interface OnLoadMoreListener{
        void  onLoadMoreReu();
    }

    public LoadMoreViewWrapper setOnLoadMoreListener(OnLoadMoreListener loadMoreListener){
        if(loadMoreListener != null){
            this.loadMoreListener = loadMoreListener;
        }
        return this;
    }
}
