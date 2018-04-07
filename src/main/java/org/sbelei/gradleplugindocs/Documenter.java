package org.sbelei.gradleplugindocs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.Options;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

public class Documenter {

	//TODO SB : rework
	File outputDirectory = getDefaultOutputDirectory();
	Map<String, Object> model = new HashMap<>();

	public void than(String string) {
		// TODO Auto-generated method stub
		generateModel();
		performDocumentGeneration();
	}

	private void generateModel() {
		model.put("build_gradle", "foo");
		model.put("gradle_command", "bar");
		model.put("gradle_result", "hello");

	}

	private void performDocumentGeneration() {
//		Options options = new Options();
//		options.setAttributes(new Attributes("snippets=custom"));
//		String converted = Asciidoctor.Factory.create().convert("{snippets}", options);
//		System.out.println(converted);

		outputDirectory.mkdirs();
		try {
			InputStream fis = getClass().getClassLoader().getResourceAsStream("org/sbelei/gradleplugindocs/asciidoctor/templates/default-task.snippet");
			Template tmpl = Mustache.compiler().compile(new InputStreamReader(fis));
			Writer writer = new PrintWriter(new FileOutputStream(new File(outputDirectory,"test.txt")));
			tmpl.execute(model, writer);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void when_gradle(String string) {
		// TODO Auto-generated method stub

	}

	public void given(String s) {

	}

	//copy from : org.springframework.restdocs.ManualRestDocumentation.getDefaultOutputDirectory()
	private static File getDefaultOutputDirectory() {
		if (new File("pom.xml").exists()) {
			return new File("target/generated-snippets");
		}
		return new File("build/generated-snippets");
	}

}
