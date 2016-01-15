package com.github.sebtom.goeuro.service.query;

import static org.junit.Assert.assertEquals;

import com.github.sebtom.goeuro.model.Location;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class GoEuroLocationParserTest {

    private GoEuroLocationParser objectUnderTest;

    @Before
    public void setUp() throws Exception {
        objectUnderTest = new GoEuroLocationParser();
    }

    @Test
    public void shouldParseEmpty() throws IOException {
        //given
        InputStream input = getInputData("empty.json");

        //when
        List<Location> locations = objectUnderTest.parse(input);

        //then
        assertEquals(0, locations.size());
    }

    @Test
    public void shouldParseList() throws IOException {
        //given
        InputStream input = getInputData("list.json");

        //when
        List<Location> locations = objectUnderTest.parse(input);

        //then
        assertEquals(8, locations.size());
    }

    @Test
    public void shouldParseAllFields() throws IOException {
        //given
        InputStream input = getInputData("allFields.json");

        //when
        List<Location> locations = objectUnderTest.parse(input);

        //then
        assertEquals(1, locations.size());

        Location location = locations.get(0);
        assertEquals("376217", location.getId());
        assertEquals("4111111", location.getKey());
        assertEquals("Berlin", location.getName());
        assertEquals("Berlin, Germany", location.getFullName());
        assertEquals("TXL", location.getAirportCode());
        assertEquals("location", location.getType());
        assertEquals("Germany", location.getCountry());
        assertEquals("52.52437", location.getPosition().getLatitude());
        assertEquals("13.41053", location.getPosition().getLongitude());
        assertEquals("8384", location.getLocationId());
        assertEquals(true, location.getIsInEurope());
        assertEquals("DE", location.getCountryCode());
        assertEquals(true, location.getIsCoreCountry());
        assertEquals("10", location.getDistance());
    }

    @Test
    public void shouldParseNullPosition() throws IOException {
        //given
        InputStream input = getInputData("emptyGeo.json");

        //when
        List<Location> locations = objectUnderTest.parse(input);

        //then
        assertEquals(2, locations.size());

        Location location = locations.get(0);
        assertEquals("376217", location.getId());
        assertEquals(null, location.getPosition());

        location = locations.get(1);
        assertEquals("376217", location.getId());
        assertEquals(null, location.getPosition());
    }

    @Test
    public void shouldHandleUnsupportedFields() throws IOException {
        //given
        InputStream input = getInputData("unsupportedFields.json");

        //when
        List<Location> locations = objectUnderTest.parse(input);

        //then
        assertEquals(1, locations.size());
        Location location = locations.get(0);
        assertEquals("376217", location.getId());
    }

    private InputStream getInputData(String file) {
        return objectUnderTest.getClass().getResourceAsStream("/data/" + file);
    }
}
