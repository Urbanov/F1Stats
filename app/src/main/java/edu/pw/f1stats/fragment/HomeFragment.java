package edu.pw.f1stats.fragment;

import android.text.Layout;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Optional;

import edu.pw.f1stats.R;
import edu.pw.f1stats.api.RestClient;
import edu.pw.f1stats.domain.Race;
import edu.pw.f1stats.exception.RestResponseErrorHandler;
import edu.pw.f1stats.item.RacesItemView;

@EFragment(R.layout.fragment_home_layout)
public class HomeFragment extends RefreshableFragment<Race> {

    @FragmentById(value = R.id.drivers, childFragment = true)
    protected DriversStandingsFragment driversStandingsFragment;

    @FragmentById(value = R.id.constructors, childFragment = true)
    protected ConstructorsStandingsFragment constructorsStandingsFragment;

    @FragmentById(value = R.id.last_race_results, childFragment = true)
    protected RaceResultsFragment raceResultsFragment;

    @FragmentById(value = R.id.last_race, childFragment = true)
    protected RacesFragment lastRace;

    @ViewById(R.id.next_race)
    protected RacesItemView nextRaceView;

    @ViewById(R.id.empty_next_race)
    protected TextView emptyView;

    @ViewById(R.id.scroll_view)
    protected ScrollView scrollView;

    @ViewById(R.id.swipe_refresh_layout)
    protected void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @RestService
    protected void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Bean
    protected void setResponseErrorHandler(RestResponseErrorHandler responseErrorHandler) {
        this.responseErrorHandler = responseErrorHandler;
    }

    @AfterViews
    @Override
    protected void init() {
        super.init();
        setToolbarTitle(getString(R.string.app_name));
        swipeRefreshLayout.setRefreshing(true);
        getData();
    }

    @Override
    @UiThread
    protected void setData(Race data) {
        if (nextRaceView != null && data != null) {
            onSuccess();
            nextRaceView.bind(data);
        }
        else {
            onError();
        }
    }

    @Override
    @Background
    protected void getData() {
        Race race = Optional.ofNullable(restClient.getNextRace())
            .map(response -> response.getData().getSingleRace())
            .orElse(null);

        updateUI(race);
    }

    @Override
    public void onRefresh() {
        driversStandingsFragment.onRefresh();
        constructorsStandingsFragment.onRefresh();
        lastRace.onRefresh();
        raceResultsFragment.onRefresh();
        getData();
    }

    private void onSuccess() {
        Optional.ofNullable(nextRaceView).ifPresent(view -> view.setVisibility(View.VISIBLE));
        Optional.ofNullable(emptyView).ifPresent(view -> view.setVisibility(View.GONE));
    }

    private void onError() {
        Optional.ofNullable(nextRaceView).ifPresent(view -> view.setVisibility(View.GONE));
        Optional.ofNullable(emptyView).ifPresent(view -> view.setVisibility(View.VISIBLE));
    }
}
