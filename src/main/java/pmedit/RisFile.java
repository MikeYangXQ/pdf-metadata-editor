package pmedit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.samskivert.mustache.Mustache;

import pmedit.CommandLine.ParseError;


public class RisFile {
	public String TY; // TY 	Type of reference (must be the first tag)
	public String A1; // A1 	First Author
	public String A2; // A2 	Secondary Author (each author on its own line preceded by the tag)
	public String A3; // A3 	Tertiary Author (each author on its own line preceded by the tag)
	public String A4; // A4 	Subsidiary Author (each author on its own line preceded by the tag)
	public String AB; // AB 	Abstract
	public String AD; // AD 	Author Address
	public String AN; // AN 	Accession Number
	public List<String> AU; // AU 	Author (each author on its own line preceded by the tag)
	public String AV; // AV 	Location in Archives
	public String BT; // BT 	This field can contain alphanumeric characters. There is no practical limit to the length of this field.
	public String C1; // C1 	Custom 1
	public String C2; // C2 	Custom 2
	public String C3; // C3 	Custom 3
	public String C4; // C4 	Custom 4
	public String C5; // C5 	Custom 5
	public String C6; // C6 	Custom 6
	public String C7; // C7 	Custom 7
	public String C8; // C8 	Custom 8
	public String CA; // CA 	Caption
	public String CN; // CN 	Call Number
	public String CP; // CP 	This field can contain alphanumeric characters. There is no practical limit to the length of this field.
	public String CT; // CT 	Title of unpublished reference
	public String CY; // CY 	Place Published
	public String DA; // DA 	Date
	public String DB; // DB 	Name of Database
	public String DO; // DO 	DOI
	public String DP; // DP 	Database Provider
	public String ED; // ED 	Editor
	public String EP; // EP 	End Page
	public String ET; // ET 	Edition
	public String ID; // ID 	Reference ID
	public String IS; // IS 	Issue number
	public String J1; // J1 	Periodical name: user abbreviation 1. This is an alphanumeric field of up to 255 characters.
	public String J2; // J2 	Alternate Title (this field is used for the abbreviated title of a book or journal name, the latter mapped to T2)
	public String JA; // JA 	Periodical name: standard abbreviation. This is the periodical in which the article was (or is to be, in the case of in-press references) published. This is an alphanumeric field of up to 255 characters.
	public String JF; // JF 	Journal/Periodical name: full format. This is an alphanumeric field of up to 255 characters.
	public String JO; // JO 	Journal/Periodical name: full format. This is an alphanumeric field of up to 255 characters.
	public List<String> KW; // KW 	Keywords (keywords should be entered each on its own line preceded by the tag)
	public String L1; // L1 	Link to PDF. There is no practical limit to the length of this field. URL addresses can be entered individually, one per tag or multiple addresses can be entered on one line using a semi-colon as a separator.
	public String L2; // L2 	Link to Full-text. There is no practical limit to the length of this field. URL addresses can be entered individually, one per tag or multiple addresses can be entered on one line using a semi-colon as a separator.
	public String L3; // L3 	Related Records. There is no practical limit to the length of this field.
	public String L4; // L4 	Image(s). There is no practical limit to the length of this field.
	public String LA; // LA 	Language
	public String LB; // LB 	Label
	public String LK; // LK 	Website Link
	public String M1; // M1 	Number
	public String M2; // M2 	Miscellaneous 2. This is an alphanumeric field and there is no practical limit to the length of this field.
	public String M3; // M3 	Type of Work
	public String N1; // N1 	Notes
	public String N2; // N2 	Abstract. This is a free text field and can contain alphanumeric characters. There is no practical length limit to this field.
	public String NV; // NV 	Number of Volumes
	public String OP; // OP 	Original Publication
	public String PB; // PB 	Publisher
	public String PP; // PP 	Publishing Place
	public String PY; // PY 	Publication year (YYYY/MM/DD)
	public String RI; // RI 	Reviewed Item
	public String RN; // RN 	Research Notes
	public String RP; // RP 	Reprint Edition
	public String SE; // SE 	Section
	public String SN; // SN 	ISBN/ISSN
	public String SP; // SP 	Start Page
	public String ST; // ST 	Short Title
	public String T1; // T1 	Primary Title
	public String T2; // T2 	Secondary Title (journal title, if applicable)
	public String T3; // T3 	Tertiary Title
	public String TA; // TA 	Translated Author
	public String TI; // TI 	Title
	public String TT; // TT 	Translated Title
	public String U1; // U1 	User definable 1. This is an alphanumeric field and there is no practical limit to the length of this field.
	public String U2; // U2 	User definable 2. This is an alphanumeric field and there is no practical limit to the length of this field.
	public String U3; // U3 	User definable 3. This is an alphanumeric field and there is no practical limit to the length of this field.
	public String U4; // U4 	User definable 4. This is an alphanumeric field and there is no practical limit to the length of this field.
	public String U5; // U5 	User definable 5. This is an alphanumeric field and there is no practical limit to the length of this field.
	public String UR; // UR 	URL
	public String VL; // VL 	Volume number
	public String VO; // VO 	Published Standard number
	public String Y1; // Y1 	Primary Date
	public String Y2; // Y2 	Access Date
	public String ER; 	// ER 	End of Reference (must be empty and the last tag)	}
	
	public List<String> A_ () {
		List<String> result = new ArrayList<String>();
		if(AU != null) { result.addAll(AU); }
		if(A1 != null) { result.add(A1); }
		if(A2 != null) { result.add(A2); }		
		if(A3 != null) { result.add(A3); }
		if(A4 != null) { result.add(A4); }
		return result;		
	}
	
	public List<String> C_ () {
		List<String> result = new ArrayList<String>();
		if(C1 != null) { result.add(C1);}
		if(C2 != null) { result.add(C2);}
		if(C3 != null) { result.add(C3);}
		if(C4 != null) { result.add(C4);}
		if(C5 != null) { result.add(C5);}
		if(C6 != null) { result.add(C6);}
		if(C7 != null) { result.add(C7);}
		if(C8 != null) { result.add(C8);}

		return result;		
	}	
	
	public List<String> U_ () {
		List<String> result = new ArrayList<String>();
		if(U1 != null) { result.add(U1);}
		if(U2 != null) { result.add(U2);}
		if(U3 != null) { result.add(U3);}
		if(U4 != null) { result.add(U4);}
		if(U5 != null) { result.add(U5);}


		return result;		
	}	
	public void setField(String name, String value){
		try {
			Field field = this.getClass().getField(name);
			boolean isList = List.class.isAssignableFrom(field.getType());
			if(isList) {
				List<String> o = (List<String>) field.get(this);
				if (o != null) {
					o.add(value);
				} else {
					List<String> l = new ArrayList<String>();
					l.add(value);
					field.set(this,l);
				}
			} else {
				field.set(this, value);
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	static Pattern recordPattern = Pattern.compile("A-ZA-Z  -");

	public static RisFile load(String file) throws FileNotFoundException{
		return load(new File(file));
	}	
	
	public static RisFile load(File file) throws FileNotFoundException{
		RisFile risData = new RisFile();
		Scanner scan = new Scanner(file);
		scan.useDelimiter("\\n|  -");
		try {
			while (scan.hasNext()){
				String cmd = scan.next();
				String value = scan.hasNext() ? scan.next().trim(): null;
				risData.setField(cmd, value);
				//System.out.println("CMD:" + cmd);
				//System.out.println("VALUE:"+ value);
			}
		}catch (InputMismatchException e){
			System.err.print(e+": ");
			System.err.println(e.getMessage());
		
		}
		return risData;
	}
	
	
	public static void main(final String[] args) {
		try {
			RisFile risFile = RisFile.load(args[0]);
			String template = "{{#A_}}{{^-first}}\n{{/-first}}{{this}}{{/A_}}";
			
			System.out.println(Mustache.compiler().compile(template).execute(risFile));
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
}

