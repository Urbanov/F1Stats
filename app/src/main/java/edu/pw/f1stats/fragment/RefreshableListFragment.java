package edu.pw.f1stats.fragment;

import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.pw.f1stats.adapter.BaseListAdapter;

public abstract class RefreshableListFragment<T> extends RefreshableFragment<List<T>> {

    protected ListView listView;

    protected TextView emptyView;

    protected BaseListAdapter<T> adapter;

    @Override
    protected void init() {
        super.init();
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyView);
        if (adapter.isEmpty()) {
            swipeRefreshLayout.setRefreshing(true);
            getData();
        }
    }

    @Override
    protected void setData(List<T> data) {
        adapter.setItems(data);
        adapter.notifyDataSetChanged();
    }
}
