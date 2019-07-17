package com.kitcut.helloworld.baserestapi.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonComponent
public class JsonDateSerializer {
    private final static SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat formatterDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //Serializer
    //formatterDate
    public static class DateJsonSerializer
            extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(formatterDate.format(value));
        }
    }

    //formatterDateTime
    public static class DateTimeJsonSerializer
            extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(formatterDateTime.format(value));
        }
    }

    //Deserializer
    //formatterDate
    public static class DateJsonDeserializer
            extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String date = jsonparser.getText();
            try {
                return formatterDate.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //formatterDateTime
//    public static class DateTimeJsonDeserializer
//            extends JsonDeserializer<Date> {
//
//        @Override
//        public Date deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//            String date = jsonparser.getText();
//            try {
//                return formatterDateTime.parse(date);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//    public static class SqlTimeDeserializer extends JsonDeserializer<Time> {
//
//        @Override
//        public Time deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
//            return Time.valueOf(jp.getValueAsString() + ":00");
//        }
//
//    }
//
//    public static class SqlTimeSerializer extends JsonSerializer<Time> {
//
//        @Override
//        public void serialize(Time time, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//
//        }
//    }
}
