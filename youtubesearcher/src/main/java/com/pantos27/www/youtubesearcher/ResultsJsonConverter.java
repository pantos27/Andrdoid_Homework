package com.pantos27.www.youtubesearcher;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by Veierovioum on 07/03/2016.
 */
public class ResultsJsonConverter extends TypeAdapter<String[]> {
//    @Override
//    public String[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        return new String[0];
//    }

    @Override
    public void write(JsonWriter out, String[] value) throws IOException {

    }

    @Override
    public String[] read(JsonReader in) throws IOException {


        return new String[0];
    }
}
