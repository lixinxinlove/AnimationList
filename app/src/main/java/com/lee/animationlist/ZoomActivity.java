package com.lee.animationlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lee.animationlist.adapter.ZoomListViewAdapter;
import com.lee.animationlist.view.ZoomListView;

import java.util.ArrayList;
import java.util.List;

public class ZoomActivity extends AppCompatActivity {
    private ZoomListView listView;
    private ZoomListViewAdapter adapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        listView = (ZoomListView) findViewById(R.id.list_view);
        initData();
        listView.setAdapter(adapter);

        View headerView = View.inflate(this, R.layout.listview_header, null);
        ImageView imageView = (ImageView) headerView.findViewById(R.id.image_view);
        listView.setImageView(imageView);
        listView.addHeaderView(headerView);

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("美女" + i);
        }
        adapter = new ZoomListViewAdapter(this, mData);
    }
}
