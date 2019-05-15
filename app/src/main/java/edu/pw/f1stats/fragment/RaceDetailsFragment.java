package edu.pw.f1stats.fragment;

import android.text.TextUtils;

import androidx.viewpager.widget.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;

import edu.pw.f1stats.R;
import edu.pw.f1stats.adapter.BasePagerAdapter;
import edu.pw.f1stats.domain.Race;

@EFragment(R.layout.fragment_viewpager)
public class RaceDetailsFragment extends BaseFragment {

    @FragmentArg
    protected Race race;

    @ViewById(R.id.view_pager)
    protected ViewPager viewPager;

    @AfterViews
    protected void init() {
        setToolbarTitle(TextUtils.join(" ", Arrays.asList(race.getSeason(), race.getName())));
        BasePagerAdapter basePagerAdapter = new BasePagerAdapter(getChildFragmentManager());
        basePagerAdapter.addFragment(getString(R.string.tab_race_results), RaceResultsFragment_.builder().race(race).build());
        basePagerAdapter.addFragment(getString(R.string.tab_qualifying_results), QualifyingResultsFragment_.builder().race(race).build());
        basePagerAdapter.addFragment(getString(R.string.tab_post_race_standings), DriversStandingsFragment_.builder().race(race).build());
        basePagerAdapter.addFragment(getString(R.string.tab_report), WebViewFragment_.builder().url(race.getUrl()).build());
        viewPager.setAdapter(basePagerAdapter);
    }
}
