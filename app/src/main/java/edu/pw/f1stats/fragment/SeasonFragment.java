package edu.pw.f1stats.fragment;


import android.text.TextUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;

import edu.pw.f1stats.R;
import edu.pw.f1stats.adapter.BasePagerAdapter;
import edu.pw.f1stats.domain.Season;

@EFragment(R.layout.fragment_viewpager)
public class SeasonFragment extends BaseFragment {

    @ViewById(R.id.view_pager)
    protected ViewPager viewPager;

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    @FragmentArg
    protected Season season;

    @AfterViews
    protected void init() {
        setToolbarTitle(TextUtils.join(" ", Arrays.asList(season.getSeason(), getString(R.string.season_title))));
        BasePagerAdapter basePagerAdapter = new BasePagerAdapter(getChildFragmentManager());
        basePagerAdapter.addFragment(getString(R.string.tab_drivers), DriversStandingsFragment_.builder().season(season).build());
        basePagerAdapter.addFragment(getString(R.string.tab_constructors), ConstructorsStandingsFragment_.builder().season(season).build());
        basePagerAdapter.addFragment(getString(R.string.tab_schedule), RacesFragment_.builder().season(season).build());
        viewPager.setAdapter(basePagerAdapter);
    }
}
