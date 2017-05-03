package io.vertPress.com.adapter;

import java.lang.reflect.Type;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import io.vertPress.com.utils.DateUtil;

public class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
  
  @Override
  public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    if (StringUtils.isNumeric(json.getAsString())) {
      return new Date(Long.valueOf(json.getAsString()));
    } else {
      return DateUtil.Str2D(json.getAsString());
    }
  }

  @Override
  public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
    String value = "";
    if (src != null) {
      value = DateUtil.Date2Str(src);
    }
    return new JsonPrimitive(value);
  }

}
