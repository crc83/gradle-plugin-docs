package org.sbelei.gradleplugindocs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.gradle.testkit.runner.GradleRunner;

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

	private BuildScript build;

	public PluginDoc(String pluginName, String pluginDescription) {
		this.pluginName = pluginName;
		this.pluginDescription = pluginDescription;
		try {
			this.build =  new BuildScript();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void config(String config) {
		this.config = config;
	}

	public void task(String task) throws IOException {
		build.addLinesToBuild("plugins {");
		//TODO : Add prerequired plugins here
		build.addLinesToBuild("	id '"+pluginName+"'");
		build.addLinesToBuild("}");
		build.addLinesToBuild(config);

		build.addLinesToBuild("version = '0.1.0'");
		build.addLinesToBuild("group = 'test.group'");


		this.task = task;

		GradleRunner.create()
			.withProjectDir(build.testProjectDir.getRoot())
			.withArguments(task)
			.forwardOutput()
			.build();
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
