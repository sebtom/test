package com.github.sebtom.goeuro;

import com.github.sebtom.goeuro.utils.ClasspathConfig;

public class AppConfig extends ClasspathConfig {

    private static final String CONFIG_PATH = "/META-INF/config.properties";

    private AppConfig() {
        super(CONFIG_PATH);
    }

    @Override
    protected boolean isValid() {
        return getQueryGoEuroEndpoint() != null
                && getOutputCsvPath() != null
                && getOutputCsvFileName() != null;
    }

    public String getQueryGoEuroEndpoint() {
        return properties.getProperty("query.goeuro.endpoint");
    }

    public String getOutputCsvPath() {
        return properties.getProperty("output.csv.path");
    }

    public String getOutputCsvFileName() {
        return properties.getProperty("output.csv.filename");
    }

    //Singleton stuff
    private static AppConfig instance;

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
}
