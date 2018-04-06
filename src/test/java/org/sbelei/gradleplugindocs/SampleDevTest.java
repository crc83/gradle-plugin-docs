package org.sbelei.gradleplugindocs;

import static org.junit.Assert.*;

import org.junit.Test;

public class SampleDevTest {

	Documenter $ = new Documenter();

	@Test
	public void testSampleInvocation() throws Exception {
		$.given("apply plugin:'my-plugin'");

		$.when_gradle("runTask");

		$.than("we have everything updated");
		assertTrue(true);
	}

}
