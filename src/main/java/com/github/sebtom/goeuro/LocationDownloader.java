package com.github.sebtom.goeuro;

import com.github.sebtom.goeuro.model.Location;
import com.github.sebtom.goeuro.service.query.LocationQueryService;
import com.github.sebtom.goeuro.service.store.LocationStore;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Reusable business logic
 */
@Slf4j
@AllArgsConstructor
public class LocationDownloader {

    private LocationQueryService reader;

    private LocationStore writer;

    public LocationStore.Summary download(String cityName) {
        log.info("Downloading locations for: '{}'", cityName);
        List<Location> locations = reader.findLocationsByCityName(cityName);
        return writer.save(locations);
    }
}
