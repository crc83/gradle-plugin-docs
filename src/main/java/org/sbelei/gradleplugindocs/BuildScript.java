package org.sbelei.gradleplugindocs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.junit.rules.TemporaryFolder;

public class BuildScript {

	public TemporaryFolder testProjectDir;
	private File buildFile;
	private File settingsFile;
	private File gradleProperties;
	private List<File> pluginClasspath;

	/**
	 *
	 * @throws IOException throwing exception from there because of no chance to recover inside constructor
	 */
	public BuildScript() throws IOException {
		testProjectDir = new TemporaryFolder();
		testProjectDir.create();
		settingsFile = testProjectDir.newFile("settings.gradle");
		buildFile = testProjectDir.newFile("build.gradle");
		buildFile.createNewFile();
		gradleProperties = testProjectDir.newFile("gradle.properties");
		pluginClasspath = preparePluginClassPath();
	}

	public void addLinesToBuild(String line) throws IOException {
		//Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter writer = Files.newBufferedWriter(
				Paths.get(buildFile.getAbsolutePath()), StandardOpenOption.APPEND))
		{
		    writer.write(line);
		}
	}

	/**
	 * Gradle test kit workaround.
	 * Collect all classpath to file. All listed in "plugin-classpath.txt" of file will be on classpath during tests.
	 * @return
	 */
	private List<File> preparePluginClassPath() {
		return new ArrayList<>();
//		pluginClasspathResource = getClass().getClassLoader().findResource("plugin-classpath.txt");
//		if (pluginClasspathResource == null) {
//			throw new IllegalStateException("Did not find plugin classpath resource, run `testClasses` build task.")
//		}
//
//		pluginClasspathResource.readLines().collect { new File(it) }
	}

}
