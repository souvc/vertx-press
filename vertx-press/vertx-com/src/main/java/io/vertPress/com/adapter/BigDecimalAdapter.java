package io.vertPress.com.adapter;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class BigDecimalAdapter implements JsonSerializer<BigDecimal>, JsonDeserializer<BigDecimal> {

  @Override
  public BigDecimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    if (StringUtils.isNotBlank(json.getAsString())) {
    	return json.getAsBigDecimal();
    }
    return new BigDecimal(0);
  }

  @Override
  public JsonElement serialize(BigDecimal src, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(src.toString());
  }

}
