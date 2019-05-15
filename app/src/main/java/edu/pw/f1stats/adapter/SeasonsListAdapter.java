package edu.pw.f1stats.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import edu.pw.f1stats.domain.Season;
import edu.pw.f1stats.item.SeasonsItemView;
import edu.pw.f1stats.item.SeasonsItemView_;

@EBean
public class SeasonsListAdapter extends BaseListAdapter<Season> implements Filterable {

    private List<Season> unfiltered;

    @RootContext
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SeasonsItemView itemView;
        if (convertView == null) {
            itemView = SeasonsItemView_.build(context);
        }
        else {
            itemView = (SeasonsItemView_) convertView;
        }
        itemView.bind(getItem(position));
        return itemView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                results.values = unfiltered.stream()
                    .filter(season -> String.valueOf(season.getSeason()).toLowerCase().contains(constraint.toString().toLowerCase()))
                    .collect(Collectors.toList());

                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                items = (List<Season>) results.values;
                SeasonsListAdapter.super.notifyDataSetChanged();
            }
        };
    }

    @Override
    public void setItems(List<Season> items) {
        Collections.reverse(items);
        unfiltered = items;
        super.setItems(items);
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
