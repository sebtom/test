package com.github.sebtom.goeuro.service.store;

import com.github.sebtom.goeuro.model.Location;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface LocationStore {

    Summary save(List<Location> locations);

    @Getter
    @AllArgsConstructor
    class Summary {

        private int totalCount;

        private int invalidEntries;

        private String output;
    }
}
