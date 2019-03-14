package com.pingxx.demoapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by sunkai on 15/3/17.
 */
public class GoodsAdapter extends BaseAdapter {
    private List<Good> mList;
    private Context mContext;

    public GoodsAdapter(Context context, List<Good> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return null == mList ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.goods_item,null);
            viewHolder = new ViewHolder();
            viewHolder.addCount = (Button)convertView.findViewById(R.id.addCount);
            viewHolder.reduceCount =(Button)convertView.findViewById(R.id.reduceCount);
            viewHolder.count = (TextView)convertView.findViewById(R.id.count);
            viewHolder.name =(TextView)convertView.findViewById(R.id.name);
            viewHolder.price =(TextView)convertView.findViewById(R.id.price);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Good good = mList.get(position);
        viewHolder.count.setText(String.valueOf(good.getCount()));
        viewHolder.name.setText(good.getName());
        viewHolder.price.setText(String.valueOf(good.getPrice()));
        viewHolder.image.setImageDrawable(mContext.getResources().getDrawable(good.getImgRes()));
        viewHolder.addCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                good.setCount(good.getCount() + 1);
                notifyDataSetChanged();
            }
        });
        viewHolder.reduceCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (good.getCount() > 1) {
                    good.setCount(good.getCount() - 1);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        Button addCount;
        Button reduceCount;
        TextView name;
        TextView count;
        TextView price;
        ImageView image;
    }
}
