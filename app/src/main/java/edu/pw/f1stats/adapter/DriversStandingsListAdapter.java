package edu.pw.f1stats.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import edu.pw.f1stats.domain.DriversStandingsItem;
import edu.pw.f1stats.item.DriversStandingsItemView;
import edu.pw.f1stats.item.DriversStandingsItemView_;

@EBean
public class DriversStandingsListAdapter extends BaseListAdapter<DriversStandingsItem> {

    @RootContext
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DriversStandingsItemView itemView;
        if (convertView == null) {
            itemView = DriversStandingsItemView_.build(context);
        }
        else {
            itemView = (DriversStandingsItemView_) convertView;
        }
        itemView.bind(getItem(position));
        itemView.setBackgroundColor(setPodiumColors(position));
        return itemView;
    }
}
