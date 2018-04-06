package org.sbelei.gradleplugindocs;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.Options;

public class Documenter {

	public void than(String string) {
		// TODO Auto-generated method stub
		performDocumentGeneration();
	}

	private void performDocumentGeneration() {
		Options options = new Options();
		options.setAttributes(new Attributes("snippets=custom projectdir=../../.."));
		String converted = Asciidoctor.Factory.create().convert("{snippets}", options);
		System.out.println(converted);
//		assertThat(converted, containsString("custom"));
	}

	public void when_gradle(String string) {
		// TODO Auto-generated method stub

	}

	public void given(String s) {

	}

}
