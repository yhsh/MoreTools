package com.qfy.listviewallselcet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class ListitemAdapter extends BaseAdapter {

    private List<DataHolder> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public ListitemAdapter(Context context, List<DataHolder> list) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.check_list_item, null);
            holder.mTitle = (TextView) convertView.findViewById(R.id.title);
            holder.mSubTitile = (TextView) convertView.findViewById(R.id.subtitle);
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTitle.setText((String) mList.get(position).titleStr);
        holder.mSubTitile.setText((String) mList.get(position).subTitleStr);
        holder.mCheckBox.setChecked(mList.get(position).checked);
        return convertView;
    }

    public class ViewHolder {
        public TextView mTitle;
        public TextView mSubTitile;
        public CheckBox mCheckBox;
    }
}
