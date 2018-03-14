package com.menglingpeng.baservadapter;

/**
 * Created by mengdroid on 2018/3/14.
 */

public interface ItemView<T> {

    void getItemViewLayoutId();
    void convertView(ViewHolder holder, T t, int position);
    boolean isViewType(T t, int position);
}
