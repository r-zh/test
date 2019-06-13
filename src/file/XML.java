package file;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XML {
	public static void main(String[] args) {
		String file_path = "/Users/tonyzhou/Desktop/University/logging/merge.xml";
		XML(file_path);
	}

	public static void XML(String file_path) {

		ArrayList<String> file = ReaderAndWriter.readToString(file_path);

		for (int i = 0; i < file.size(); i++) {
			if (file.get(i).contains(".xml") && LogDef.isLLOC(file.get(i + 1))) {
				// System.out.println(file.get(i));
				// System.out.println(file.get(i+1)); //taint point with log
				try {
					SAXReader sax = new SAXReader();
					StringReader read = new StringReader(file.get(i + 1));
					Document document = sax.read(read);
					Element root = document.getRootElement();

					List<Element> results = root.element("Results").elements();
					for (Element result : results) {
						if (LogDef.isLLOC(result.element("Sink").attribute("Statement").toString())) {
							System.out.println("-------------------------------------------------");
							System.out.println("Application: "+file.get(i));
							System.out
									.println("Sink Point: " + result.element("Sink").attribute("Statement").getData());
							System.out.println(
									"         Method: " + result.element("Sink").attribute("Method").getData());
							System.out.println();
							List<Element> sources = result.element("Sources").elements();
							for (Element source : sources) {
								System.out.println("Source Statement: " + source.attribute("Statement").getData());
								System.out.println("             Method: " + source.attribute("Method").getData());
							}
							System.out.println();
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}
}
