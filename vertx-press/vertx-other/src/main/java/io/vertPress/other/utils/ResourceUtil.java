package io.vertPress.other.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName: ResourceLoader
 * @Description: TODO 资源加载
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月23日 下午8:25:05
 * 
 */
public class ResourceUtil {

	private static ResourceUtil loader = new ResourceUtil();
	private static Map<String, Properties> loadermap = new HashMap<String, Properties>();
	private static String DEFAULT_CONFIG_FILE = "vertx-search-url.properties";

	private ResourceUtil() {
	}

	public static ResourceUtil getInstance() {
		return loader;
	}

	public Properties getPropFromProperties(String fileName) throws Exception {
		Properties prop = loadermap.get(fileName);
		if (prop != null)
			return prop;
		String path = getClass().getClassLoader().getResource(fileName).getPath();
		prop = new Properties();
		prop.load(new FileInputStream(new File(path)));
		loadermap.put(fileName, prop);
		return prop;
	}

	public String getString(String key) {
		try {
			Properties prop = ResourceUtil.getInstance().getPropFromProperties(DEFAULT_CONFIG_FILE);
			String value = prop.getProperty(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public Integer getInt(String key) throws Exception {
		return Integer.parseInt(getString(key));
	}

	public Long getLong(String key) throws NumberFormatException, Exception {
		return Long.parseLong(getString(key));
	}

	public Boolean getBoolean(String key) throws Exception {
		return Boolean.parseBoolean(getString(key));
	}
}
