package edu.pw.f1stats.item;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.Optional;

import edu.pw.f1stats.R;
import edu.pw.f1stats.domain.RaceResult;

@EViewGroup(R.layout.race_results_item_list)
public class RaceResultsItemView extends LinearLayout {

    @ViewById(R.id.position)
    protected TextView position;

    @ViewById(R.id.driver_name)
    protected TextView driverName;

    @ViewById(R.id.laps)
    protected TextView laps;

    @ViewById(R.id.time)
    protected TextView time;

    @ViewById(R.id.grid)
    protected TextView grid;

    @ViewById(R.id.points)
    protected TextView points;

    public RaceResultsItemView(Context context) {
        super(context);
    }

    public void bind(RaceResult result) {
        position.setText(result.getPosition());
        driverName.setText(result.getDriver().getFullName());
        laps.setText(result.getLaps());
        time.setText(Optional.ofNullable(result.getTime()).orElse(result.getStatus()));
        grid.setText(result.getGrid());
        points.setText(result.getPoints());
    }
}
