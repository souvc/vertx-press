package io.vertPress.other;

import java.io.File;
import java.io.IOException;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @ClassName: Runner
 * @Description: TODO 启动测试
 * @author FoamValue foamvalue@live.cn
 * @date 2017年3月7日 下午1:08:03
 * 
 */
public class Runner {
	private static final String WEB_EXAMPLES_DIR = "vertx-other";
	private static final String WEB_EXAMPLES_JAVA_DIR = WEB_EXAMPLES_DIR + "/src/main/java/";
	private static final String WEB_EXAMPLES_JS_DIR = WEB_EXAMPLES_DIR + "/src/main/js/";

	public static void runClusteredExample(Class<?> clazz) {
		runExample(WEB_EXAMPLES_JAVA_DIR, clazz, new VertxOptions().setClustered(true), null);
	}

	public static void runExample(Class<?> clazz) {
		runExample(WEB_EXAMPLES_JAVA_DIR, clazz, new VertxOptions().setClustered(false), null);
	}

	public static void runExample(Class<?> clazz, DeploymentOptions options) {
		runExample(WEB_EXAMPLES_JAVA_DIR, clazz, new VertxOptions().setClustered(false), options);
	}

	// JavaScript examples

	public static void runJSExample(String scriptName) {
		runScriptExample(WEB_EXAMPLES_JS_DIR, scriptName, new VertxOptions().setClustered(false));
	}

	public static void runJSExampleClustered(String scriptName) {
		runScriptExample(WEB_EXAMPLES_JS_DIR, scriptName, new VertxOptions().setClustered(true));
	}

	static class JSAuthRunner {
		public static void main(String[] args) {
			Runner.runJSExample("io/vertx/example/web/auth/server.js");
		}
	}

	static class JSAuthJDBC {
		public static void main(String[] args) {
			Runner.runJSExample("io/vertx/example/web/authjdbc/server.js");
		}
	}

	static class JSHelloWorldRunner {
		public static void main(String[] args) {
			Runner.runJSExample("io/vertx/example/web/helloworld/server.js");
		}
	}

	static class JSRealtimeRunner {
		public static void main(String[] args) {
			Runner.runJSExample("io/vertx/example/web/realtime/server.js");
		}
	}

	static class JSChatRunner {
		public static void main(String[] args) {
			Runner.runJSExample("io/vertx/example/web/chat/server.js");
		}
	}

	static class JSSessionsRunner {
		public static void main(String[] args) {
			Runner.runJSExample("io/vertx/example/web/sessions/server.js");
		}
	}

	static class JSTemplatingRunner {
		public static void main(String[] args) {
			Runner.runJSExample("io/vertx/example/web/templating/mvel/server.js");
		}
	}

	public static void runExample(String exampleDir, Class<?> clazz, VertxOptions options,
			DeploymentOptions deploymentOptions) {
		runExample(exampleDir + clazz.getPackage().getName().replace(".", "/"), clazz.getName(), options,
				deploymentOptions);
	}

	public static void runScriptExample(String prefix, String scriptName, VertxOptions options) {
		File file = new File(scriptName);
		String dirPart = file.getParent();
		String scriptDir = prefix + dirPart;
		runExample(scriptDir, scriptDir + "/" + file.getName(), options, null);
	}

	public static void runExample(String exampleDir, String verticleID, VertxOptions options,
			DeploymentOptions deploymentOptions) {
		if (options == null) {
			// Default parameter
			options = new VertxOptions();
		}
		// Smart cwd detection

		// Based on the current directory (.) and the desired directory
		// (exampleDir), we try to compute the vertx.cwd
		// directory:
		try {
			// We need to use the canonical file. Without the file name is .
			File current = new File(".").getCanonicalFile();
			if (exampleDir.startsWith(current.getName()) && !exampleDir.equals(current.getName())) {
				exampleDir = exampleDir.substring(current.getName().length() + 1);
			}
		} catch (IOException e) {
			// Ignore it.
		}

		System.setProperty("vertx.cwd", exampleDir);
		Consumer<Vertx> runner = vertx -> {
			try {
				if (deploymentOptions != null) {
					vertx.deployVerticle(verticleID, deploymentOptions);
				} else {
					vertx.deployVerticle(verticleID);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		};
		if (options.isClustered()) {
			Vertx.clusteredVertx(options, res -> {
				if (res.succeeded()) {
					Vertx vertx = res.result();
					runner.accept(vertx);
				} else {
					res.cause().printStackTrace();
				}
			});
		} else {
			Vertx vertx = Vertx.vertx(options);
			runner.accept(vertx);
		}
	}
}
