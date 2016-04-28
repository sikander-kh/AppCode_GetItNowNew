package com.drawer.navigation.clipboard.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.drawer.navigation.clipboard.R;
import com.drawer.navigation.clipboard.db.AssetTableRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thana on 10/4/16.
 */
public class ClipboardAdapter extends BaseAdapter {


    List<AssetTableRecord> myList = new ArrayList<AssetTableRecord>();
    LayoutInflater inflater;
    Context context;


    public ClipboardAdapter(Context context, List<AssetTableRecord> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public AssetTableRecord getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_child, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        AssetTableRecord currentListData = getItem(position);

        mViewHolder.tvTitle.setText(currentListData.getTextData());


        return convertView;
    }

    private class MyViewHolder {
        TextView tvTitle, tvDesc;

        public MyViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.tvTitle);

        }
    }
}

