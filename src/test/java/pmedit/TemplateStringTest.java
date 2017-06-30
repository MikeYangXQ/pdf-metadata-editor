package pmedit;

import static org.junit.Assert.*;

import org.junit.Test;

import pmedit.MetadataInfo;
import pmedit.TemplateStringSimple;


public class TemplateStringTest {

	@Test
	public void testProcess() {
		MetadataInfo md = new MetadataInfo();
		md.set("doc.title", "basic_title_1");
		md.set("doc.keywords", "basic_keywords_2_1,basic_keywords_2_2,basic_keywords_2_3");
		assertEquals("",new TemplateString("{nonexistent}").process(md) );
		assertEquals("{{{{",new TemplateStringSimple("{{{{").process(md) );
		assertEquals("{{",new TemplateStringSimple("{{}{{").process(md) );
		assertEquals("basic_title_1",new TemplateStringSimple("{doc.title}").process(md) );
		assertEquals("basic_title_1{",new TemplateStringSimple("{doc.title}{").process(md) );
		assertEquals("basic_title_1}",new TemplateStringSimple("{doc.title}}").process(md) );
		assertEquals("basic_title_1",new TemplateStringSimple("{doc.title}{}").process(md) );
		assertEquals("basic_title_1-basic_keywords_2_1,basic_keywords_2_2,basic_keywords_2_3",
				new TemplateStringSimple("{doc.title}-{doc.keywords}").process(md) );

		assertEquals("-ba",
				new TemplateStringSimple("{doc.title}-{doc.keywords}", 3).process(md) );
		assertEquals("basi-basic_keywords_",
				new TemplateStringSimple("{doc.title}-{doc.keywords}", 20).process(md) );
		assertEquals("1234567890",
				new TemplateStringSimple("1234567890", 3).process(md) );
		assertEquals("ba1234567890",
				new TemplateStringSimple("{doc.title}1234567890", 12).process(md) );
	}

}
