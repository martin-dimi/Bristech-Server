package com.bristech.utils;


import com.google.api.client.util.Charsets;
import com.google.common.io.CharStreams;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@SuppressWarnings("unused")
public class MeetupUtils {

    private static final Logger LOGGER = LogManager.getLogger(MeetupUtils.class);

    private static final String MAIN_URL = "https://api.meetup.com/";
    private static final String EVENTS_PATH = "2/events";
    private static final String BRISTECH_QUERY = "group_urlname";
    private static final String BRISTECH_GROUP = "bristech";
    private static final String STATUS_QUERY = "status";
    private static final String STATUS_PAST = "upcoming,past";
    private static final String SIGN_QUERY = "sign";
    private static final String SIGN_TRUE = "true";
    private static final String API_KEY_QUERY = "key";
    private static final String API_KEY = "1e126921a726454b13424d21d5d";


    public static URL getUpcomingEventsURL() {
        URL meetupURL = null;

        try {

            URIBuilder builder = new URIBuilder(MAIN_URL);
            builder.setPath(EVENTS_PATH);
            builder.addParameter(BRISTECH_QUERY, BRISTECH_GROUP);
            builder.addParameter(SIGN_QUERY, SIGN_TRUE);
            builder.addParameter(API_KEY_QUERY, API_KEY);

            meetupURL = builder.build().toURL();

            LOGGER.info(meetupURL.toString());

        } catch (URISyntaxException e) {
            LOGGER.error("Meetup main url is not properly formed");
        } catch (MalformedURLException e) {
            LOGGER.error("Couldn't form a valid url");
        }

        return meetupURL;
    }

    public static URL getAllEventsURL() {
        URL meetupURL = null;

        try {
            URIBuilder builder = new URIBuilder(MAIN_URL);
            builder.setPath(EVENTS_PATH);
            builder.addParameter(BRISTECH_QUERY, BRISTECH_GROUP);
            builder.addParameter(STATUS_QUERY, STATUS_PAST);
            builder.addParameter(SIGN_QUERY, SIGN_TRUE);
            builder.addParameter(API_KEY_QUERY, API_KEY);

            meetupURL = builder.build().toURL();

        } catch (URISyntaxException e) {
            LOGGER.error("Meetup main url is not properly formed");
        } catch (MalformedURLException e) {
            LOGGER.error("Couldn't form a valid url");
        }

        return meetupURL;
    }

    public static String getResponseFromURL(URL url) {

        String result = null;
        if (url == null) {
            LOGGER.warn("URL given is null.");
            return null;
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream input = connection.getInputStream();

            result = CharStreams.toString(new InputStreamReader(input, Charsets.UTF_8));
            connection.disconnect();
        } catch (IOException e) {
            LOGGER.error("Couldn't get a response from url");
        }

        return result;
    }

}
