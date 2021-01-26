package com.publicissapient.football.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

/**
 * @author mukutbhattacharjee
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Position {
    private String countryName;
    private String countryId;
    private String leagueName;
    private String leagueId;
    private String teamName;
    private String teamId;
    private String overAllLeaguePosition;
}
