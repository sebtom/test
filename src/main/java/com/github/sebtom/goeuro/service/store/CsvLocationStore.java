package com.github.sebtom.goeuro.service.store;

import com.github.sebtom.goeuro.AppConfig;
import com.github.sebtom.goeuro.exception.AppException;
import com.github.sebtom.goeuro.model.Location;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Writer for CSV output
 */
@Slf4j
public class CsvLocationStore implements LocationStore {

    private AppConfig config = AppConfig.getInstance();

    private CsvLocationRowPrinter rowPrinter = new CsvLocationRowPrinter();

    @Override
    public Summary save(List<Location> locations) {
        Path file = Paths.get(buildPath());
        Path output = prepareFile(file);
        if (locations == null || locations.isEmpty()) {
            return new Summary(0, 0, "");
        }
        int invalidEntries = 0;
        try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(output, Charset.forName("UTF-8")))) {
            for (Location location : locations) {
                out.println(rowPrinter.printLocation(location));
                if (!isValid(location)) {
                    invalidEntries++;
                }
            }
        } catch (IOException e) {
            throw new AppException(String.format("Error while writing a file: %s.", file.getFileName()), e);
        }
        return new Summary(locations.size(), invalidEntries, output.toAbsolutePath().toString());
    }

    private String buildPath() {
        return config.getOutputCsvPath() + File.separator + config.getOutputCsvFileName();
    }

    private Path prepareFile(Path file) {
        try {
            Files.deleteIfExists(file);
            return Files.createFile(file);
        } catch (IOException e) {
            throw new AppException(String.format("Could not prepare output file: %s.", file.getFileName()), e);
        }
    }

    private boolean isValid(Location location) {
        return location.getId() != null
                && location.getName() != null
                && location.getType() != null
                && location.getPosition() != null
                && location.getPosition().getLatitude() != null
                && location.getPosition().getLongitude() != null;
    }
}
