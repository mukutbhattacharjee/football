package com.publicissapient.football.service;

import com.publicissapient.football.config.AppProperties;
import com.publicissapient.football.model.Country;
import com.publicissapient.football.model.League;
import com.publicissapient.football.model.Position;
import com.publicissapient.football.model.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @author mukutbhattacharjee
 */
@Slf4j
@Service
public class FootballService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppProperties properties;

    public List<Country> getCountries() {
        return restTemplate.getForObject(properties.getApiUrl(),
                List.class,
                "get_countries");
    }

    public List<League> getLeagues(String countryId) {
        return restTemplate.getForObject(properties.getApiUrl() + "&country_id={country_id}",
                List.class,
                "get_leagues", countryId);
    }

    public List<Team> getTeams(String countryId, String leagueId) {
        StringBuilder url = new StringBuilder(properties.getApiUrl());
        if (Objects.nonNull(countryId)) {
            url.append("&country_id={country_id}");
        }
        if (Objects.nonNull(leagueId)) {
            url.append("&league_id={country_id}");
        }
        return restTemplate.getForObject(url.toString(),
                List.class,
                "get_teams", countryId, leagueId);
    }


    public List<Position> getPositions(String leagueId) {
        return restTemplate.getForObject(properties.getApiUrl() + "&league_id={league_id}",
                List.class,
                "get_standings", leagueId);
    }
}
