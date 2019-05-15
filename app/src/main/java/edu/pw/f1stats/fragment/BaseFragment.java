package edu.pw.f1stats.fragment;

import androidx.fragment.app.Fragment;

import edu.pw.f1stats.activity.MainActivity;

public abstract class BaseFragment extends Fragment {

    @SuppressWarnings("ConstantConditions")
    protected void setToolbarTitle(String title) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);
    }
}
