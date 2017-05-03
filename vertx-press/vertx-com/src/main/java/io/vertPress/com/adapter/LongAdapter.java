package io.vertPress.com.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LongAdapter implements JsonSerializer<Long>, JsonDeserializer<Long> {

  @Override
  public Long deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
    return arg0.getAsLong();
  }

  @Override
  public JsonElement serialize(Long arg0, Type arg1, JsonSerializationContext arg2) {
    String value = "";
    if (arg0 != null) {
      value = String.valueOf(arg0);
    }
    return new JsonPrimitive(value);
  }

}
