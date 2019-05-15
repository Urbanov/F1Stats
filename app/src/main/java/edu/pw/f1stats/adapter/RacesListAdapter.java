package edu.pw.f1stats.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import edu.pw.f1stats.domain.Race;
import edu.pw.f1stats.item.RacesItemView;
import edu.pw.f1stats.item.RacesItemView_;

@EBean
public class RacesListAdapter extends BaseListAdapter<Race> {

    @RootContext
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RacesItemView itemView;
        if (convertView == null) {
            itemView = RacesItemView_.build(context);
        }
        else {
            itemView = (RacesItemView_) convertView;
        }
        itemView.bind(getItem(position));
        return itemView;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
