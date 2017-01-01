package com.lee.animationlist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lee.animationlist.R;

import java.util.List;

/**
 * Created by lixinxin on 2017/1/1.
 * 邮箱 895330766@qq.com
 */

public class ZoomListViewAdapter extends BaseAdapter {


    private Context mContext;
    private List<String> mData;

    public ZoomListViewAdapter(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = View.inflate(mContext, R.layout.item_view, null);

        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(mData.get(i));
        return view;
    }
}
