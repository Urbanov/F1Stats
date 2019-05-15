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
import edu.pw.f1stats.adapter.RaceResultsListAdapter;
import edu.pw.f1stats.api.RestClient;
import edu.pw.f1stats.domain.Race;
import edu.pw.f1stats.domain.RaceResult;
import edu.pw.f1stats.domain.Races;
import edu.pw.f1stats.domain.Response;
import edu.pw.f1stats.exception.RestResponseErrorHandler;

@EFragment(R.layout.fragment_swipe_race_results_listview)
public class RaceResultsFragment extends RefreshableListFragment<RaceResult> {

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
    protected void setRaceResultsListAdapter(RaceResultsListAdapter raceResultsListAdapter) {
        this.adapter = raceResultsListAdapter;
    }

    @Bean
    protected void setResponseErrorHandler(RestResponseErrorHandler responseErrorHandler) {
        this.responseErrorHandler = responseErrorHandler;
    }

    @FragmentArg
    protected Race race;

    @AfterViews
    @Override
    protected void init() {
        super.init();
    }

    @Override
    @UiThread
    protected void setData(List<RaceResult> data) {
        super.setData(data);
    }

    @Override
    @Background
    protected void getData() {
        Response<Races> response = Optional.ofNullable(race)
            .map(r -> restClient.getRaceResultsBySeasonAndRound(race.getSeason(), race.getRound()))
            .orElseGet(() -> restClient.getLastRaceResults(Integer.valueOf(getString(R.string.home_fetch_limit))));

        Race singleRace = Optional.ofNullable(response)
            .map(r -> response.getData().getSingleRace())
            .orElse(null);

        List<RaceResult> results = Optional.ofNullable(singleRace)
            .map(Race::getRaceResults)
            .orElseGet(Collections::emptyList);

        updateUI(results);
    }
}
