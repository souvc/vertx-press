package io.vertPress.manage.init;

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
public class ResourceLoader {

	private static ResourceLoader loader = new ResourceLoader();
	private static Map<String, Properties> loadermap = new HashMap<String, Properties>();
	private static String DEFAULT_CONFIG_FILE = "vertx.properties";

	private ResourceLoader() {
	}

	public static ResourceLoader getInstance() {
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

	public String getString(String key) throws Exception {
		Properties prop = ResourceLoader.getInstance().getPropFromProperties(DEFAULT_CONFIG_FILE);
		String value = prop.getProperty(key);
		return value;
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
