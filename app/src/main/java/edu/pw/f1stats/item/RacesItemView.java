package edu.pw.f1stats.item;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import edu.pw.f1stats.R;
import edu.pw.f1stats.domain.Race;
import edu.pw.f1stats.utils.CountryUtils;

@EViewGroup(R.layout.races_item_list)
public class RacesItemView extends LinearLayout {

    @ViewById(R.id.race_name)
    protected TextView raceName;

    @ViewById(R.id.circuit_name)
    protected TextView circuitName;

    @ViewById(R.id.round)
    protected TextView round;

    @ViewById(R.id.date)
    protected TextView date;

    @ViewById(R.id.flag)
    protected ImageView flag;

    @Bean
    protected CountryUtils countryUtils;

    public RacesItemView(Context context) {
        super(context);
    }

    public RacesItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(Race race) {
        raceName.setText(race.getName());
        circuitName.setText(race.getCircuit().getName());
        round.setText(String.valueOf(race.getRound()));
        date.setText(race.getDate());
        String imageName = countryUtils.getCodeByCountry(race.getCircuit().getCountry());
        flag.setBackgroundResource(getResources().getIdentifier(imageName, "drawable", getContext().getPackageName()));
    }
}
