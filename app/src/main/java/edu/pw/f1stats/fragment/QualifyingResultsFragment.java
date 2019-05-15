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
import edu.pw.f1stats.adapter.QualifyingResultsListAdapter;
import edu.pw.f1stats.api.RestClient;
import edu.pw.f1stats.domain.QualifyingResult;
import edu.pw.f1stats.domain.Race;
import edu.pw.f1stats.exception.RestResponseErrorHandler;

@EFragment(R.layout.fragment_swipe_qualifying_results_listview)
public class QualifyingResultsFragment extends RefreshableListFragment<QualifyingResult> {

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
    protected void setQualifyingResultsListAdapter(QualifyingResultsListAdapter qualifyingResultsListAdapter) {
        this.adapter = qualifyingResultsListAdapter;
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
    protected void setData(List<QualifyingResult> data) {
        super.setData(data);
    }

    @Override
    @Background
    protected void getData() {
        Race singleRace = Optional.ofNullable(restClient.getQualifyingResultsBySeasonAndRound(race.getSeason(), race.getRound()))
            .map(r -> r.getData().getSingleRace())
            .orElse(null);

        List<QualifyingResult> results = Optional.ofNullable(singleRace)
            .map(Race::getQualifyingResults)
            .orElseGet(Collections::emptyList);

        updateUI(results);
    }
}
