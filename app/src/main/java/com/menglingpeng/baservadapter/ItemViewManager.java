package com.menglingpeng.baservadapter;

import android.support.v4.util.SparseArrayCompat;

/**
 * Created by mengdroid on 2018/3/14.
 */

public class ItemViewManager<T> {

    private SparseArrayCompat<T> itemViews = new SparseArrayCompat<>();

    public int getItemViewCount(){
        return itemViews.size();
    }

    public ItemViewManager<T> addItemView(ItemView<T> itemView){
        int viewType = itemViews.size();
        if(itemView != null){
            itemViews.put(viewType, (T) itemView);
            viewType++;
        }
        return this;
    }

    public ItemViewManager<T> removeItemView(ItemView<T> itemView){
        if(itemView == null){
            throw new NullPointerException("ItemView is null");
        }
        int itemViewToRemove = itemViews.indexOfValue((T) itemView);
        if(itemViewToRemove >= 0){
            itemViews.removeAt(itemViewToRemove);
        }
        return this;
    }
}
