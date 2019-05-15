package edu.pw.f1stats.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import edu.pw.f1stats.domain.RaceResult;
import edu.pw.f1stats.item.RaceResultsItemView;
import edu.pw.f1stats.item.RaceResultsItemView_;

@EBean
public class RaceResultsListAdapter extends BaseListAdapter<RaceResult> {

    @RootContext
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RaceResultsItemView itemView;
        if (convertView == null) {
            itemView = RaceResultsItemView_.build(context);
        }
        else {
            itemView = (RaceResultsItemView_) convertView;
        }
        itemView.bind(getItem(position));
        itemView.setBackgroundColor(setPodiumColorsWithAlternatingRest(position));
        return itemView;
    }
}
