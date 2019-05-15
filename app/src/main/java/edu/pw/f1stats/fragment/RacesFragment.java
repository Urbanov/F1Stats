package edu.pw.f1stats.fragment;

import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import edu.pw.f1stats.R;
import edu.pw.f1stats.adapter.RacesListAdapter;
import edu.pw.f1stats.api.RestClient;
import edu.pw.f1stats.domain.Race;
import edu.pw.f1stats.domain.Races;
import edu.pw.f1stats.domain.Response;
import edu.pw.f1stats.domain.Season;
import edu.pw.f1stats.exception.RestResponseErrorHandler;
import edu.pw.f1stats.utils.FragmentUtils;

@EFragment(R.layout.fragment_swipe_common_listview)
public class RacesFragment extends RefreshableListFragment<Race> {

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
    protected void setRacesListAdapter(RacesListAdapter racesListAdapter) {
        this.adapter = racesListAdapter;
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
    @Background
    protected void getData() {
        Response<Races> response = Optional.ofNullable(season)
                .map(s -> restClient.getRacesBySeason(season.getSeason()))
                .orElseGet(() -> restClient.getLastRace());

        List<Race> races = Optional.ofNullable(response)
                .map(r -> response.getData().getRaces())
                .orElseGet(Collections::emptyList);

        updateUI(races);
    }

    @Override
    @UiThread
    protected void setData(List<Race> data) {
        super.setData(data);
    }

    @SuppressWarnings("ConstantConditions")
    @ItemClick(R.id.list_view)
    protected void raceSelected(int position) {
        Fragment fragment = RaceDetailsFragment_.builder()
            .race(adapter.getItem(position))
            .build();

        FragmentUtils.replace(getActivity(), fragment, R.id.container);
    }
}
