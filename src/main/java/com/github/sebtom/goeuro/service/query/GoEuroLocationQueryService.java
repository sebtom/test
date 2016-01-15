package com.github.sebtom.goeuro.service.query;

import com.github.sebtom.goeuro.AppConfig;
import com.github.sebtom.goeuro.exception.AppException;
import com.github.sebtom.goeuro.model.Location;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Reader for GoEuro Query API
 */
@Slf4j
public class GoEuroLocationQueryService implements LocationQueryService {

    private AppConfig config = AppConfig.getInstance();

    private GoEuroLocationParser parser = new GoEuroLocationParser();

    @Override
    public List<Location> findLocationsByCityName(String cityName) {
        String queryUrl = buildQueryUrl(cityName);
        URL url = getUrl(queryUrl);
        log.info("Querying REST url: '{}'.", url.toString());
        return queryLocations(url);
    }

    private String buildQueryUrl(String cityName) {
        return config.getQueryGoEuroEndpoint() + getEncodedCity(cityName);
    }

    private String getEncodedCity(String cityName) {
        try {
            return URLEncoder.encode(cityName, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new AppException(String.format("Exception encoding query: '%s'.", cityName), e);
        }
    }

    private URL getUrl(String queryUrl) {
        try {
            return new URL(queryUrl);
        } catch (MalformedURLException e) {
            throw new AppException(String.format("Specified URL is invalid: '%s'.", queryUrl), e);
        }
    }

    private List<Location> queryLocations(URL url) {
        try {
            InputStream input = url.openStream();
            return parser.parse(input);
        } catch (IOException e) {
            throw new AppException(String.format("Could not read from: '%s'.", url), e);
        }
    }
}
