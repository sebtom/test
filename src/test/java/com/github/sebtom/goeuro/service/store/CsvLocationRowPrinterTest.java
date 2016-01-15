package com.github.sebtom.goeuro.service.store;

import static org.junit.Assert.assertEquals;

import com.github.sebtom.goeuro.model.Location;
import com.github.sebtom.goeuro.model.Position;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class CsvLocationRowPrinterTest {

    private CsvLocationRowPrinter objectUnderTest;

    @Before
    public void setUp() throws Exception {
        objectUnderTest = new CsvLocationRowPrinter();
    }

    @Test
    public void shouldPrintEmptyRow() throws IOException {
        //given
        Location location = new Location();

        //when
        String printedLocation = objectUnderTest.printLocation(location);

        //then
        assertEquals(", , , , ", printedLocation);
    }

    @Test
    public void shouldPrintSimpleRow() throws IOException {
        //given
        Location location = createDefaultLocation();

        //when
        String printedLocation = objectUnderTest.printLocation(location);

        //then
        assertEquals(readExpectedOutput("/output/simple.csv"), printedLocation);
    }

    @Test
    public void shouldHandleQuotes() throws IOException {
        //given
        Location location = createDefaultLocation()
                .setName("City \"name\" quoted");

        //when
        String printedLocation = objectUnderTest.printLocation(location);

        //then
        assertEquals(readExpectedOutput("/output/quotes.csv"), printedLocation);
    }

    private String readExpectedOutput(String file) throws IOException {
        InputStream input = getClass().getResourceAsStream(file);

        return IOUtils.toString(input, "UTF-8");
    }

    private Location createDefaultLocation() {
        return new Location()
                .setId("376217")
                .setName("Berlin")
                .setType("location")
                .setPosition(new Position()
                        .setLatitude("52.52437")
                        .setLongitude("13.41053"));
    }
}
