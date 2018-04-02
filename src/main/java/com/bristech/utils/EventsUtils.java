package com.bristech.utils;

import com.bristech.entities.Event;
import com.google.common.base.Strings;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class EventsUtils {

    private static final Logger LOGGER = LogManager.getLogger(EventsUtils.class);
    private static final String ELEM_RESULT = "results";
    private static final String DATE_FORMAT = "EEE MMM dd yyyy HH:mm:ss Z";


    public static List<Event> getEventsFromJSON(String json) {

        if (Strings.isNullOrEmpty(json)) {
            LOGGER.warn("JSON IS EMPTY.");
            return null;
        }

        List<Event> events;
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .disableHtmlEscaping()
                .registerTypeAdapter(Date.class,
                        (JsonDeserializer<Date>) (jsonElement, type, context) -> new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                .create();

        JsonParser parser = new JsonParser();

        JsonObject response = (JsonObject) parser.parse(json);
        JsonArray eventsJson = response.getAsJsonArray(ELEM_RESULT);
        events = gson.fromJson(eventsJson, new TypeToken<List<Event>>(){}.getType());

        return events;
    }

}
