package edu.pw.f1stats.fragment;


import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import edu.pw.f1stats.api.RestClient;
import edu.pw.f1stats.exception.RestResponseErrorHandler;

public abstract class RefreshableFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected RestClient restClient;

    protected SwipeRefreshLayout swipeRefreshLayout;

    protected RestResponseErrorHandler responseErrorHandler;

    protected void updateUI(T data) {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        setData(data);
    }

    @Override
    public void onRefresh() {
        getData();
    }

    protected void init() {
        swipeRefreshLayout.setOnRefreshListener(this);
        restClient.setRestErrorHandler(responseErrorHandler);
    }

    protected abstract void setData(T data);

    protected abstract void getData();
}
