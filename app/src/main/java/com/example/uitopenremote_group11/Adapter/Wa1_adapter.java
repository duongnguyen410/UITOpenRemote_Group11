package com.example.uitopenremote_group11.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uitopenremote_group11.Model.Asset_data;
import com.example.uitopenremote_group11.R;

import java.util.List;

public class Wa1_adapter extends BaseAdapter {
    private final Context context;
    private final int layout;
    private final List<Asset_data> as_dataList;

    public Wa1_adapter(Context context, int layout, List<Asset_data> as_dataList) {
        this.context = context;
        this.layout = layout;
        this.as_dataList = as_dataList;
    }

    @Override
    public int getCount() {
        return as_dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private static class ViewHolder{
        TextView assetName;
        TextView subItem;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.assetName = (TextView) view.findViewById(R.id.assetName);
            holder.subItem = (TextView) view.findViewById(R.id.subItem);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        Asset_data assetData = as_dataList.get(i);
        String position = String.valueOf(i+1);
        holder.assetName.setText(position + "." + assetData.getName());
        holder.subItem.setText("Id: " + assetData.getId() + " / Name: " + assetData.getName() + " / Temperature: " + assetData.getTemperature()
                + " / Temp_timestamp: " + Long.parseLong(assetData.getTemp_Timestamp()) + " / Humidity: " + assetData.getHumidity() + " / Hum_timestamp: " + Long.parseLong(assetData.getHum_timestamp())
                + " / WindSpeed: " + assetData.getWindSpeed() + " / WindS_timestamp: " + Long.parseLong(assetData.getWindS_timestamp())
                + " / Date: " + assetData.getDate() + " / Month: " + assetData.getMonth() + " / Year: " + assetData.getYear());

        return view;
    }
}
