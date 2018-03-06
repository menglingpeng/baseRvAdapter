package com.menglingpeng.baservadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mengdroid on 2018/3/6.
 */

public class ViewHolder extends RecyclerView.ViewHolder{

    private View convertView;
    private Context context;
    private ArrayList<View> views;

    public ViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.convertView = itemView;
        views = new ArrayList<>();
    }

    public static ViewHolder createViewHolder(Context context, View itemView){
        ViewHolder holder = new ViewHolder(context, itemView);
        return holder;
    }

    public static ViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId){
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(context,itemView);
        return holder;
    }

    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if(view == null){
            view = convertView.findViewById(viewId);
            views.add(view);
        }
        return (T)view;
    }

    public View getConvertView(){
        return convertView;
    }

    //各种View的设置方法
    public ViewHolder setText(int viewId, String text){
        TextView textView = getView(viewId);
        textView.setText(text);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId){
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap){
        ImageView imageView = getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolder setImageDrawable(int viewId, Drawable drawable){
        ImageView imageView = getView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    //事件处理
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
