package pmedit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;


public class TemplateStringBug {

//	@Test
//	public void testSingleBrace() {
//		Template compiled = Mustache.compiler().escapeHTML(false).defaultValue("").withDelims("{ }").compile("{doc.title}{1}");
//
//		Map<String, String> data = new HashMap<String, String>();
//		data.put("doc.title", "title_1");
//		assertEquals("title_1",compiled.execute(data) );
//	}

	@Test
	public void testDeoubleBrace() {
		Template compiled = Mustache.compiler().escapeHTML(false).defaultValue("").compile("{{doc.title}}{{}}");

		Map<String, String> data = new HashMap<String, String>();
		data.put("doc.title", "title_1");
		assertEquals("title_1",compiled.execute(data) );
	}

}
