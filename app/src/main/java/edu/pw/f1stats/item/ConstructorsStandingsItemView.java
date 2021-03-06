package edu.pw.f1stats.item;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import edu.pw.f1stats.R;
import edu.pw.f1stats.domain.ConstructorsStandingsItem;
import edu.pw.f1stats.utils.CountryUtils;

@EViewGroup(R.layout.constructors_standings_item_list)
public class ConstructorsStandingsItemView extends LinearLayout {

    @ViewById(R.id.constructor_name)
    protected TextView constructorName;

    @ViewById(R.id.points)
    protected TextView points;

    @ViewById(R.id.position)
    protected TextView position;

    @ViewById(R.id.flag)
    protected ImageView flag;

    @Bean
    protected CountryUtils countryUtils;

    public ConstructorsStandingsItemView(Context context) {
        super(context);
    }

    public void bind(ConstructorsStandingsItem constructorsStandingsItem) {
        constructorName.setText(constructorsStandingsItem.getConstructor().getName());
        points.setText(constructorsStandingsItem.getPoints());
        position.setText(constructorsStandingsItem.getPosition());
        String imageName = countryUtils.getCodeByNationality(constructorsStandingsItem.getConstructor().getNationality());
        flag.setBackgroundResource(getResources().getIdentifier(imageName, "drawable", getContext().getPackageName()));
    }
}
