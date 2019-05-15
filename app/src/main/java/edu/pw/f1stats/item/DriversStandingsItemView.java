package edu.pw.f1stats.item;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import edu.pw.f1stats.R;
import edu.pw.f1stats.domain.DriversStandingsItem;
import edu.pw.f1stats.utils.CountryUtils;

@EViewGroup(R.layout.drivers_standings_item_list)
public class DriversStandingsItemView extends LinearLayout {

    @ViewById(R.id.driver_name)
    protected TextView driverName;

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

    public DriversStandingsItemView(Context context) {
        super(context);
    }

    public void bind(DriversStandingsItem driversStandingsItem) {
        driverName.setText(driversStandingsItem.getDriver().getFullName());
        constructorName.setText(driversStandingsItem.getConstructor().getName());
        points.setText(driversStandingsItem.getPoints());
        position.setText(driversStandingsItem.getPosition());
        String imageName = countryUtils.getCodeByNationality(driversStandingsItem.getDriver().getNationality());
        flag.setBackgroundResource(getResources().getIdentifier(imageName, "drawable", getContext().getPackageName()));
    }

}
