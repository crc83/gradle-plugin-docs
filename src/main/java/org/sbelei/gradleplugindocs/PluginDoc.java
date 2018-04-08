package org.sbelei.gradleplugindocs;

import java.util.HashMap;
import java.util.Map;

/**
 * Just a bean with tons of setters
 * It describes plugin and it's tasks
 *
 * Some names do not follow Java Code Conventions
 * to make code more readable in groovy
 *
 * @author Serhii Belei
 *
 */
public class PluginDoc {

	private String pluginName;
	private String pluginDescription;
	private String config;
	private String task;
	private String task_description;
	private String configDescription;
	private String since;

	public PluginDoc(String pluginName, String pluginDescription) {
		this.pluginName = pluginName;
		this.pluginDescription = pluginDescription;
	}

	public void config(String config) {
		this.config = config;
	}

	public void task(String task) {
		this.task = task;
	}

	public void task_description(String taskDescription) {
		this.task_description = taskDescription;
	}

	public void config_description(String configDescription) {
		this.configDescription = configDescription;
	}

	public void since(String since) {
		this.since = since;
	}

	public void doGenerate() {
		Map<String, Object> model = new HashMap<>();
		model.put("plugin_name",pluginName);
		model.put("plugin_description",pluginDescription);
		model.put("config",config);
		model.put("task_name",task);
		model.put("task_description",task_description);
		model.put("config_description",configDescription);
		model.put("since",since);

		new Documenter(pluginName, model).generate();
	}
}
