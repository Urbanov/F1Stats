package edu.pw.f1stats.item;

import android.content.Context;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.Arrays;

import edu.pw.f1stats.R;
import edu.pw.f1stats.domain.Season;

@EViewGroup(R.layout.seasons_item_list)
public class SeasonsItemView extends LinearLayout {

    @ViewById(R.id.name)
    TextView name;

    public SeasonsItemView(Context context) {
        super(context);
    }

    public void bind(Season season) {
        name.setText(TextUtils.join(" ", Arrays.asList(season.getSeason(), getContext().getString(R.string.season_title))));
    }
}
