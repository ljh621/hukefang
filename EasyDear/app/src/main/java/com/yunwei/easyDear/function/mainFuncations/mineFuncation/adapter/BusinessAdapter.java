package com.yunwei.easyDear.function.mainFuncations.mineFuncation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.view.RoundedBitmapImageViewTarget;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 12:45
 * Version:1.0
 */

public class BusinessAdapter extends BaseAdapter {

    private Context context;

    private String[] total=new String [4];

    public BusinessAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return total.length;
    }

    @Override
    public Object getItem(int position) {
        return total[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_business_layout,null);
            holder=new ViewHolder();
            holder.imageView=(ImageView)convertView.findViewById(R.id.ItemBusiness_icon_iv);
            holder.textView=(TextView)convertView.findViewById(R.id.ItemBusiness_name_textView);

            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.textView.setText("Item"+(position+1));
        Glide.with(context).load("http://4.pic.58control.cn/p2/small/n_s12316981804746993063.jpg").asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(holder.imageView));
        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
