package edu.pw.f1stats.activity;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import edu.pw.f1stats.R;
import edu.pw.f1stats.fragment.HomeFragment;
import edu.pw.f1stats.fragment.HomeFragment_;
import edu.pw.f1stats.fragment.SeasonChooseFragment_;
import edu.pw.f1stats.utils.FragmentUtils;

@SuppressLint("Registered")
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById(R.id.nav_view)
    protected NavigationView navigationView;

    @ViewById(R.id.drawer_layout)
    protected DrawerLayout drawer;

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;

    @AfterViews
    protected void init() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(drawerView.getWindowToken(), 0);
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        navigateToHome();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            if (getSupportFragmentManager().getFragments().stream().anyMatch(fragment -> fragment instanceof HomeFragment)) {
                finish();
                moveTaskToBack(true);
            }
            else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_home:
                navigateToHome();
                break;

            case R.id.nav_archive:
                FragmentUtils.replace(this, SeasonChooseFragment_.builder().build(), R.id.container);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigateToHome() {
        FragmentUtils.replace(this, HomeFragment_.builder().build(), R.id.container);
    }
}
