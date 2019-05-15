package edu.pw.f1stats.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import edu.pw.f1stats.R;

@EFragment(R.layout.fragment_webview_layout)
public class WebViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @ViewById(R.id.webview)
    protected WebView webView;

    @ViewById(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    @FragmentArg
    protected String url;

    @SuppressLint("SetJavaScriptEnabled")
    @AfterViews
    protected void init() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public void onRefresh() {
        webView.reload();
    }
}
