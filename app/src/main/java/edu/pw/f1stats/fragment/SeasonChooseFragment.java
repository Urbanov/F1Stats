package edu.pw.f1stats.fragment;

import android.text.TextUtils;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import edu.pw.f1stats.R;
import edu.pw.f1stats.adapter.SeasonsListAdapter;
import edu.pw.f1stats.api.RestClient;
import edu.pw.f1stats.domain.Season;
import edu.pw.f1stats.exception.RestResponseErrorHandler;
import edu.pw.f1stats.utils.FragmentUtils;

@EFragment(R.layout.fragment_swipe_seasons_listview)
public class SeasonChooseFragment extends RefreshableListFragment<Season> implements SearchView.OnQueryTextListener {

    @RestService
    protected void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @ViewById(R.id.swipe_refresh_layout)
    protected void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @ViewById(R.id.list_view)
    protected void setListView(ListView listView) {
        this.listView = listView;
    }

    @ViewById(R.id.empty)
    protected void setEmptyView(TextView emptyView) {
        this.emptyView = emptyView;
    }

    @Bean
    protected void setSeasonsListAdapter(SeasonsListAdapter seasonsListAdapter) {
        this.adapter = seasonsListAdapter;
    }

    @Bean
    protected void setResponseErrorHandler(RestResponseErrorHandler responseErrorHandler) {
        this.responseErrorHandler = responseErrorHandler;
    }

    @ViewById(R.id.search_view)
    protected SearchView searchView;

    @AfterViews
    @Override
    protected void init() {
        super.init();
        setToolbarTitle(getString(R.string.archive_title));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.choose_season_hint));
    }

    @Override
    @Background
    protected void getData() {
        List<Season> seasons = Optional.ofNullable(restClient.getSeasons())
            .map(response -> response.getData().getSeasons())
            .orElseGet(Collections::emptyList);

        updateUI(seasons);
    }

    @Override
    @UiThread
    protected void setData(List<Season> data) {
        super.setData(data);
        onQueryTextChange(String.valueOf(searchView.getQuery()));
    }

    @SuppressWarnings("ConstantConditions")
    @ItemClick(R.id.list_view)
    protected void seasonSelected(int position) {
        searchView.clearFocus();
        Fragment fragment = SeasonFragment_.builder()
            .season(adapter.getItem(position))
            .build();
        FragmentUtils.replace(getActivity(), fragment, R.id.container);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Filter filter = ((SeasonsListAdapter) adapter).getFilter();
        if (TextUtils.isEmpty(newText)) {
            filter.filter("");
        }
        else {
            filter.filter(newText);
        }
        return true;
    }
}
