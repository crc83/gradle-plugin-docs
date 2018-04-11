package org.sbelei.gradleplugindocs;

import static org.junit.Assert.*;

import org.junit.Test;

public class PluginDocTest {

	PluginDoc plugin = new PluginDoc("java", "Gradle java plugin");

	@Test
	public void testSimpleFlow() throws Exception {
		plugin.task("jar");
	}
}
