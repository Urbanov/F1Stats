package edu.pw.f1stats.item;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import edu.pw.f1stats.R;
import edu.pw.f1stats.domain.QualifyingResult;

@EViewGroup(R.layout.qualifying_results_item_list)
public class QualifyingResultsItemView extends LinearLayout {

    @ViewById(R.id.position)
    protected TextView position;

    @ViewById(R.id.driver_name)
    protected TextView driverName;

    @ViewById(R.id.q1)
    protected TextView q1;

    @ViewById(R.id.q2)
    protected TextView q2;

    @ViewById(R.id.q3)
    protected TextView q3;

    public QualifyingResultsItemView(Context context) {
        super(context);
    }

    public void bind(QualifyingResult result) {
        position.setText(result.getPosition());
        driverName.setText(result.getDriver().getFullName());
        q1.setText(result.getQ1());
        q2.setText(result.getQ2());
        q3.setText(result.getQ3());
    }
}
