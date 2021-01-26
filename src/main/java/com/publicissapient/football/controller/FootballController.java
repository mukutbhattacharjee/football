package com.publicissapient.football.controller;

import com.publicissapient.football.model.Country;
import com.publicissapient.football.model.League;
import com.publicissapient.football.model.Position;
import com.publicissapient.football.model.Team;
import com.publicissapient.football.service.FootballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mukutbhattacharjee
 */
@RestController
@RequestMapping("/api")
public class FootballController {

    @Autowired
    private FootballService service;

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(service.getCountries());
    }

    @GetMapping("/leagues")
    public ResponseEntity<List<League>> getCountries(@RequestParam("country") String countryId) {
        return ResponseEntity.ok(service.getLeagues(countryId));
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getTeams(@RequestParam("country") String countryId, @RequestParam("league") String leagueId) {
        return ResponseEntity.ok(service.getTeams(countryId,leagueId));
    }

    @GetMapping("/positions")
    public ResponseEntity<List<Position>> getPositions(@RequestParam("league") String leagueId) {
        return ResponseEntity.ok(service.getPositions(leagueId));
    }

}
