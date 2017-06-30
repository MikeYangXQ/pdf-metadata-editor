package pmedit;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;


public class TemplateString {
	String template;
	Template compiled;
	public TemplateString(String template, int maxOutputLenght){
		this.template = template;
		parse();
	}

	public TemplateString(String template){
		this(template,0xFFFFFFF);
	}
	
	public void parse(){
		compiled = Mustache.compiler().escapeHTML(false).defaultValue("").withDelims("{ }").compile(this.template);
	}
	
	public String process(MetadataInfo md){
		return compiled.execute(md);
	}

}
