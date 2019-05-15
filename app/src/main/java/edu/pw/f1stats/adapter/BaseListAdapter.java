package edu.pw.f1stats.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.pw.f1stats.R;

public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected List<T> items;

    protected Context context;

    public BaseListAdapter() {
        this.items = new ArrayList<>();
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    protected int setPodiumColorsWithAlternatingRest(int position) {
        if (position < 3) {
            return setPodiumColors(position);
        }
        return position % 2 == 0
            ? context.getColor(R.color.evenColor)
            : context.getColor(R.color.oddColor);
    }

    protected int setPodiumColors(int position) {
        switch (position) {
            case 0:
                return context.getColor(R.color.firstPlace);

            case 1:
                return context.getColor(R.color.secondPlace);

            case 2:
                return context.getColor(R.color.thirdPlace);

            default:
                return context.getColor(R.color.otherPlace);
        }
    }
}
