package com.menglingpeng.baservadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

    public ViewHolder setTextColor(int viewId, int textColor){
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolder setTextColorRes(int viewId, int textColorRes){
        TextView view = getView(viewId);
        view.setTextColor(context.getColor(textColorRes));
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

    public ViewHolder setBackgroudColor(int viewId, int color){
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public ViewHolder setBackgroudRes(int viewId, int res){
        View view = getView(viewId);
        view.setBackgroundResource(res);
        return this;
    }

    public ViewHolder setProgress(int viewID, int progress){
        ProgressBar view = getView(viewID);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setProgress(int viewID, int progress, int max){
        ProgressBar view = getView(viewID);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolder setProgressMax(int viewId, int max){
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolder setChecked(int viewId, boolean checked){
        Checkable view = (Checkable)getView(viewId);
        view.setChecked(checked);
        return this;
    }

    public ViewHolder setVisible(int viewId, boolean visible){
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder setAlpha(int viewId, float value){
        View view = getView(viewId);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            view.setAlpha(value);
        }else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(value, value);
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            view.startAnimation(alphaAnimation);
        }
        return this;
    }

    //事件处理
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener){
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}
