package com.jayarbautista.volleysample;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VolleyAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<InfoModel> informationList;

    public VolleyAdapter(Activity activity, List<InfoModel> informationList) {
        this.informationList = informationList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return informationList.size();
    }

    @Override
    public Object getItem(int i) {
        return informationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            inflater = LayoutInflater.from(activity);
            view = inflater.inflate(R.layout.row_listview, null);
            viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
            viewHolder.txtQuote = (TextView) view.findViewById(R.id.txtQuote);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        InfoModel info = informationList.get(i);
        viewHolder.txtName.setText(info.getName());
        viewHolder.txtQuote.setText(info.getQuote());
        return view;
    }

    class ViewHolder {
        TextView txtName;
        TextView txtQuote;
    }
}
