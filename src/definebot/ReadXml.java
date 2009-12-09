package definebot;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXml {

	public String getMeaning(String word) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parse using builder to get DOM representation of the XML file
			Document dom = db
					.parse("http://services.aonaware.com/DictService/DictService.asmx/DefineInDict?dictid=wn&word="
							+ word);
			dom.getDocumentElement().normalize();

			NodeList nodeLst = dom.getElementsByTagName("WordDefinition");
			if (nodeLst == null)
				return null;

			Node fstNode = nodeLst.item(1);
			return fstNode.getFirstChild().getNodeValue();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
}
