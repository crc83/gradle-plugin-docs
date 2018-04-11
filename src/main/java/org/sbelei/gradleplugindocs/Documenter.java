package org.sbelei.gradleplugindocs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.asciidoctor.Asciidoctor;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

/**
 * This class is responsible for document generation.
 * It takes snippet from resources and parameters from map
 * (prepared by PluginDoc)
 * @author Serhii Belei
 *
 */
class Documenter {

	private static final String BUILD_GENERATED_SNIPPETS = "build/generated-snippets";
	private static final String TEMPLATES_FOLDER = "org/sbelei/gradleplugindocs/asciidoctor/templates/";
	private String fileName;
	private Map<String, Object> model;

	public Documenter(String fileName, Map<String, Object> model) {
		this.fileName = fileName;
		this.model = model;
	}

	public void generate() {
//		Options options = new Options();
//		options.setAttributes(new Attributes("snippets=custom"));
//		String converted = Asciidoctor.Factory.create().convert("{snippets}", options);
//		System.out.println(converted);
		File outputDirectory = getDefaultOutputDirectory();
		outputDirectory.mkdirs();
		try {
			InputStream fis = getClass().getClassLoader().getResourceAsStream(TEMPLATES_FOLDER+"plugin-description-single-extension.snippet");
			Template tmpl = Mustache.compiler().compile(new InputStreamReader(fis));
			File generatedFile = new File(outputDirectory, fileName + ".adoc");
			Writer writer = new PrintWriter(new FileOutputStream(generatedFile));
			tmpl.execute(model, writer);
			writer.close();

			Asciidoctor.Factory.create().renderFile(generatedFile, new HashMap<>());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//copy from : org.springframework.restdocs.ManualRestDocumentation.getDefaultOutputDirectory()
	private static File getDefaultOutputDirectory() {
		return new File(BUILD_GENERATED_SNIPPETS);
	}

}
