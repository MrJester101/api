package com.dichotomy.google.auth.serializer;

import java.io.IOException;
import java.util.Date;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class DateAdapter extends TypeAdapter<Date> {

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        out.value(value.getTime());
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        try {
            return new Date(in.nextLong());
        } catch (NumberFormatException | IllegalStateException ex) {
            // Value is null
        }

        return new Date();
    }

}
