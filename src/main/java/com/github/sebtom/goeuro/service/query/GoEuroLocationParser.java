package com.github.sebtom.goeuro.service.query;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sebtom.goeuro.model.Location;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Reads Location data from JSON
 */
public class GoEuroLocationParser {

    public List<Location> parse(InputStream input) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(input,
                objectMapper.getTypeFactory().constructCollectionType(List.class, GoEuroLocation.class));
    }
}
