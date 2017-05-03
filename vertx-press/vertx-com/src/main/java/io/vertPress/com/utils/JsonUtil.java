package io.vertPress.com.utils;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.vertPress.com.adapter.BigDecimalAdapter;
import io.vertPress.com.adapter.DateAdapter;
import io.vertPress.com.adapter.LongAdapter;

public class JsonUtil {
	private static Gson gson = new GsonBuilder().registerTypeAdapter(Long.class, new LongAdapter())
			.registerTypeAdapter(Date.class, new DateAdapter())
			.registerTypeAdapter(BigDecimal.class, new BigDecimalAdapter()).create();

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static Object fromJson(String str, Class<?> clazz) {
		return gson.fromJson(str, clazz);
	}

	public static <T> T fromJson(String str, Type type) {
		return gson.fromJson(str, type);
	}
}
