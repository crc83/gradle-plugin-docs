package org.sbelei.gradleplugindocs

import static org.junit.Assert.*

import org.junit.Test

class SampleDevGroovyTest {

	PluginDoc d = new PluginDoc("my-plugin",
	"""
		This plugin provides functionality to do nothing.
		It's created for demo purposes only.
	""");
	@Test
	public void test() {
		d.since "1.0.0"
		d.config """
				my-plugin-config {
					param-a=2
					param-b=42
				}""" ;

		d.task "task1" ;

		//??? retrive from gradle tasks???
		d.task_description "Task provides abily to to perform action"
		d.config_description "Configuration allows to do it twice for 42"
		assertTrue(true);
		d.doGenerate()

	}

}
