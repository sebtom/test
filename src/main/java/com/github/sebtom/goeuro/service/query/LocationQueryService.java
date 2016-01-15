package com.github.sebtom.goeuro.service.query;

import com.github.sebtom.goeuro.model.Location;
import java.util.List;

public interface LocationQueryService {

    List<Location> findLocationsByCityName(String cityName);

}
