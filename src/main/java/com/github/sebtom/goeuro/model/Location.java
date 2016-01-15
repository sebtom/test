package com.github.sebtom.goeuro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Location model, should be independent from various sources
 */
@Getter
@Setter
@Accessors(chain = true)
public class Location {

    private String id;

    private String key;

    private String name;

    private String fullName;

    private String airportCode;

    private String type;

    private String country;

    private Position position;

    private String locationId;

    private Boolean isInEurope;

    private String countryCode;

    private Boolean isCoreCountry;

    private String distance;
}
