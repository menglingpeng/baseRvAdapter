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

    public ItemViewManager<T> removeItemView(int itemType){
        int itemViewToRemove = itemViews.indexOfKey(itemType);
        if(itemViewToRemove >= 0){
            itemViews.removeAt(itemViewToRemove);
        }
        return this;
    }

    public void convert(ViewHolder holder, T t, int position ){
        int count = itemViews.size();
        for(int i = 0; i < count; i++){
            ItemView<T> itemView = (ItemView<T>) itemViews.valueAt(i);
            if(itemView.isViewType(t, position)){
                itemView.convertView(holder, t, position);
                return;;
            }
        }
        throw new IllegalStateException("No ItemViewManager added that matches position = " + position + "in data " +
                "source");
    }

    public ItemView getItemView(int viewType){
        return (ItemView) itemViews.get(viewType);
    }

    public int getItemViewType(ItemView itemView){
        return itemViews.indexOfValue((T) itemView);
    }
}
