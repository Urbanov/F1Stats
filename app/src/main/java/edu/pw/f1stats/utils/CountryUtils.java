package edu.pw.f1stats.utils;

import android.content.Context;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.pw.f1stats.R;
import lombok.SneakyThrows;

@EBean(scope = EBean.Scope.Singleton)
public class CountryUtils {

    private List<HashMap<String, String>> countries;

    @RootContext
    Context context;

    @SneakyThrows
    private void loadCountriesIfNeeded() {
        if (countries == null) {
            InputStream inputStream = context.getResources().openRawResource(R.raw.countries);
            ObjectMapper mapper = new ObjectMapper();
            countries = mapper.readValue(inputStream, new TypeReference<List<HashMap<String, String>>>(){});
        }
    }

    @SuppressWarnings("ConstantConditions")
    public String getCodeByNationality(String nationalityName) {
        loadCountriesIfNeeded();
        return countries.stream()
                .filter(country -> {
                    return Arrays.stream(country.get("nationality").split(","))
                        .anyMatch(nationality -> nationality.trim().equalsIgnoreCase(nationalityName));
                })
                .findAny()
                .map(country -> country.get("alpha_2_code").toLowerCase())
                .orElse("missing");
    }

    @SuppressWarnings("ConstantConditions")
    public String getCodeByCountry(String countryName) {
        loadCountriesIfNeeded();
        return countries.stream()
            .filter(country -> {
                String code2 = country.get("alpha_2_code");
                String code3 = country.get("alpha_3_code");
                String name = country.get("en_short_name");
                String[] nationalities = country.get("nationality").split(",");

                return countryName.equalsIgnoreCase(code2)
                    || countryName.equalsIgnoreCase(code3)
                    || countryName.equalsIgnoreCase(name)
                    || Arrays.stream(nationalities).anyMatch(nationality -> nationality.trim().equalsIgnoreCase(countryName));
            })
            .findAny()
            .map(country -> country.get("alpha_2_code").toLowerCase())
            .orElse("missing");
    }
}
