package edu.pw.f1stats.fragment;

import android.widget.ListView;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import edu.pw.f1stats.R;
import edu.pw.f1stats.adapter.ConstructorsStandingsListAdapter;
import edu.pw.f1stats.api.RestClient;
import edu.pw.f1stats.domain.ConstructorsStandingsItem;
import edu.pw.f1stats.domain.ConstructorsStandings;
import edu.pw.f1stats.domain.Response;
import edu.pw.f1stats.domain.Season;
import edu.pw.f1stats.exception.RestResponseErrorHandler;

@EFragment(R.layout.fragment_swipe_common_listview)
public class ConstructorsStandingsFragment extends RefreshableListFragment<ConstructorsStandingsItem> {

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
    protected void setConstructorsStandingsListAdapter(ConstructorsStandingsListAdapter constructorsStandingsListAdapter) {
        this.adapter = constructorsStandingsListAdapter;
    }

    @Bean
    protected void setResponseErrorHandler(RestResponseErrorHandler responseErrorHandler) {
        this.responseErrorHandler = responseErrorHandler;
    }

    @FragmentArg
    protected Season season;

    @AfterViews
    @Override
    protected void init() {
        super.init();
    }

    @Override
    @UiThread
    protected void setData(List<ConstructorsStandingsItem> data) {
        super.setData(data);
    }

    @Override
    @Background
    protected void getData() {
        Response<ConstructorsStandings> response = Optional.ofNullable(season)
            .map(s -> restClient.getConstructorsStandingsBySeason(season.getSeason()))
            .orElseGet(() -> restClient.getCurrentConstructorsStandings(Integer.valueOf(getString(R.string.home_fetch_limit))));

        List<ConstructorsStandingsItem> standings = Optional.ofNullable(response)
            .map(r -> response.getData().getStandings())
            .orElseGet(Collections::emptyList);

        updateUI(standings);
    }
}
