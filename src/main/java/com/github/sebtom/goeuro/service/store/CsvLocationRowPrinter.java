package com.github.sebtom.goeuro.service.store;

import com.github.sebtom.goeuro.model.Location;

/**
 * Prints single Location as CSV row
 */
public class CsvLocationRowPrinter {

    //_id, name, type, latitude, longitude
    private static final String ROW_FORMAT = "%s, %s, %s, %s, %s";

    public String printLocation(Location location) {
        String latitude = null;
        String longitude = null;
        if (location.getPosition() != null) {
            latitude = location.getPosition().getLatitude();
            longitude = location.getPosition().getLongitude();
        }
        return String.format(ROW_FORMAT,
                prepareField(location.getId()), prepareField(location.getName()), prepareField(location.getType()),
                prepareField(latitude), prepareField(longitude));
    }

    private String prepareField(String field) {
        if (field == null) {
            return "";
        }
        return String.format("\"%s\"", field.replaceAll("\"", "\"\""));
    }

}
