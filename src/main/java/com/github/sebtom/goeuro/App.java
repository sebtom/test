package com.github.sebtom.goeuro;

import com.github.sebtom.goeuro.exception.AppException;
import com.github.sebtom.goeuro.service.query.GoEuroLocationQueryService;
import com.github.sebtom.goeuro.service.store.CsvLocationStore;
import com.github.sebtom.goeuro.service.store.LocationStore;
import com.github.sebtom.goeuro.utils.Timer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * Main class should handle only input and output
 */
@Slf4j
public class App {

    private static final Logger console = org.slf4j.LoggerFactory.getLogger(App.class.getName() + "_console");

    public static void main(String[] args) {
        try {
            safeExecute(args);
        } catch (AppException e) {
            notifyFailure(e);
        }
    }

    private static void safeExecute(String[] args) {
        Timer timer = new Timer();
        if (!isValid(args)) {
            exitFault();
            return;
        }
        String cityQuery = args[0].trim();
        console.info("Start processing for input: '{}'.", cityQuery);
        LocationDownloader downloader = new LocationDownloader(new GoEuroLocationQueryService(), new CsvLocationStore());
        LocationStore.Summary summary = downloader.download(cityQuery);
        checkResult(summary, timer);
    }

    private static boolean isValid(String[] args) {
        if ((args == null) || (args.length == 0)) {
            console.info("'CITY_NAME' is not specified. Usage example:");
            console.info("java -jar GoEuroTest.jar \"CITY_NAME\"");
            return false;
        } else if (args.length > 1) {
            console.info("More than one 'CITY_NAME' is specified, maybe you forgot quotes. Usage example:");
            console.info("java -jar GoEuroTest.jar \"CITY_NAME\"");
            return false;
        } else if (args[0].trim().length() == 0){
            console.info("'CITY_NAME' must contain some non-whitespace chars. Usage example:");
            console.info("java -jar GoEuroTest.jar \"    CITY_NAME    \"");
            return false;
        }
        return true;
    }

    private static void notifyFailure(Exception e) {
        console.info("Exception occurred while processing. Check application logs for details.");
        log.error("Exception occurred while processing.", e);
        exitFault();
    }

    private static void exitFault() {
        System.exit(1);
    }

    private static void checkResult(LocationStore.Summary summary, Timer timer) {
        if (summary.getTotalCount() == 0) {
            console.info("No matches found for specified \"CITY_NAME\".");
        } else {
            console.info("Successfully downloaded {} entries, {} of them invalid. Check output file '{}'.",
                    summary.getTotalCount(), summary.getInvalidEntries(), summary.getOutput());
        }
        console.info("Execution took {} s.", timer.getElapsedTime());

    }
}
