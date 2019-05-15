package edu.pw.f1stats.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import edu.pw.f1stats.domain.ConstructorsStandingsItem;
import edu.pw.f1stats.item.ConstructorsStandingsItemView;
import edu.pw.f1stats.item.ConstructorsStandingsItemView_;

@EBean
public class ConstructorsStandingsListAdapter extends BaseListAdapter<ConstructorsStandingsItem> {

    @RootContext
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstructorsStandingsItemView itemView;
        if (convertView == null) {
            itemView = ConstructorsStandingsItemView_.build(context);
        }
        else {
            itemView = (ConstructorsStandingsItemView_) convertView;
        }
        itemView.bind(getItem(position));
        itemView.setBackgroundColor(setPodiumColors(position));
        return itemView;
    }
}