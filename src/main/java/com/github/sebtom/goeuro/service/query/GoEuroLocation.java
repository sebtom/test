package com.github.sebtom.goeuro.service.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sebtom.goeuro.model.Location;
import com.github.sebtom.goeuro.model.Position;

/**
 * JSON mapping for GoEuro response entry
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoEuroLocation extends Location {

    @Override
    @JsonProperty(value = "_id")
    public Location setId(String value) {
        return super.setId(value);
    }

    @Override
    @JsonProperty(value = "iata_airport_code")
    public Location setAirportCode(String value) {
        return super.setAirportCode(value);
    }

    @Override
    @JsonProperty(value = "inEurope")
    public Location setIsInEurope(Boolean value) {
        return super.setIsInEurope(value);
    }

    @Override
    @JsonProperty(value = "coreCountry")
    public Location setIsCoreCountry(Boolean value) {
        return super.setIsCoreCountry(value);
    }

    @Override
    @JsonProperty(value = "geo_position")
    public Location setPosition(Position value) {
        return super.setPosition(value);
    }
}