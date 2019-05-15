package edu.pw.f1stats.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import edu.pw.f1stats.domain.QualifyingResult;
import edu.pw.f1stats.item.QualifyingResultsItemView;
import edu.pw.f1stats.item.QualifyingResultsItemView_;

@EBean
public class QualifyingResultsListAdapter extends BaseListAdapter<QualifyingResult> {

    @RootContext
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        QualifyingResultsItemView itemView;
        if (convertView == null) {
            itemView = QualifyingResultsItemView_.build(context);
        }
        else {
            itemView = (QualifyingResultsItemView_) convertView;
        }
        itemView.bind(getItem(position));
        itemView.setBackgroundColor(setPodiumColorsWithAlternatingRest(position));
        return itemView;
    }
}
