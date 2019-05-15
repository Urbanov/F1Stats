package edu.pw.f1stats.api;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.RestClientErrorHandling;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import edu.pw.f1stats.domain.ConstructorsStandings;
import edu.pw.f1stats.domain.DriversStandings;
import edu.pw.f1stats.domain.Races;
import edu.pw.f1stats.domain.Response;
import edu.pw.f1stats.domain.Seasons;

@Rest(rootUrl = "https://ergast.com/api/f1", converters = MappingJackson2HttpMessageConverter.class)
public interface RestClient extends RestClientErrorHandling {

    @Get("/{season}/driverStandings.json")
    Response<DriversStandings> getDriversStandingsBySeason(@Path Integer season);

    @Get("/{season}/{round}/driverStandings.json")
    Response<DriversStandings> getDriversStandingsBySeasonAndRound(@Path Integer season, @Path Integer round);

    @Get("/current/driverStandings.json?limit={limit}")
    Response<DriversStandings> getCurrentDriversStandings(@Path Integer limit);

    @Get("/{season}/constructorStandings.json")
    Response<ConstructorsStandings> getConstructorsStandingsBySeason(@Path Integer season);

    @Get("/current/constructorStandings.json?limit={limit}")
    Response<ConstructorsStandings> getCurrentConstructorsStandings(@Path Integer limit);

    @Get("/{season}/races.json")
    Response<Races> getRacesBySeason(@Path Integer season);

    @Get("/current/last/races.json")
    Response<Races> getLastRace();

    @Get("/current/next/races.json")
    Response<Races> getNextRace();

    @Get("/{season}/{round}/results.json")
    Response<Races> getRaceResultsBySeasonAndRound(@Path Integer season, @Path Integer round);

    @Get("/current/last/results.json?limit={limit}")
    Response<Races> getLastRaceResults(@Path Integer limit);

    @Get("/{season}/{round}/qualifying.json")
    Response<Races> getQualifyingResultsBySeasonAndRound(@Path Integer season, @Path Integer round);

    @Get("/seasons.json?limit=100")
    Response<Seasons> getSeasons();
}
