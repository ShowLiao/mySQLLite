package com.example.show.mysqllite;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by show on 8/8/17.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        Item item = getItem(position);

        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);
        }

        TextView txtID = (TextView) convertView.findViewById(R.id.txtID);
        Log.e("id", String.valueOf(item.getId()));
        txtID.setText(String.valueOf(item.getId()));
        TextView txtTask = (TextView) convertView.findViewById(R.id.txtTask);
        txtTask.setText(item.getTask());
        Log.e("id", item.getTask());
        TextView txtDate = (TextView) convertView.findViewById(R.id.txtdueDate);
        txtDate.setText(item.getDate());
        TextView txtLocation = (TextView) convertView.findViewById(R.id.txtLocation);
        txtLocation.setText(item.getLocation());
        TextView txtDetail = (TextView) convertView.findViewById(R.id.txtDetail);
        txtDetail.setText(item.getDetail());
        TextView txtPriority = (TextView) convertView.findViewById(R.id.txtPriority);
        txtPriority.setText(String.valueOf(item.getPriority()));
        if (0 == item.getPriority()) {
            txtTask.setTextColor(Color.RED);
            txtDate.setTextColor(Color.RED);
            txtPriority.setTextColor(Color.RED);
        }else if (1 == item.getPriority()) {
            txtTask.setTextColor(Color.GREEN);
            txtDate.setTextColor(Color.GREEN);
            txtPriority.setTextColor(Color.RED);
        }

        return convertView;
    }
}
