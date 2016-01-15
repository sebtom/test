package com.github.sebtom.goeuro.utils;

import com.github.sebtom.goeuro.exception.AppException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Handles operations for properties from classpath
 */
public abstract class ClasspathConfig {

    protected final Properties properties = new Properties();

    protected ClasspathConfig(String propertiesPath) {
        load(propertiesPath);
    }

    private void load(String propertiesPath) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(propertiesPath)) {
            if (inputStream == null) {
                throw new AppException(String.format("Configuration not found: '%s'.", propertiesPath));
            }

            properties.load(inputStream);
        } catch (IOException e) {
            throw new AppException(String.format("Configuration could not be loaded: '%s'.", propertiesPath), e);
        }

        if (!isValid()) {
            throw new AppException(String.format("Configuration is not valid: '%s'.", propertiesPath));
        }
    }

    protected abstract boolean isValid();

}
