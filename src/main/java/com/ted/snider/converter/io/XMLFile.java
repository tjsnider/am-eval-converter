package com.ted.snider.converter.io;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.ted.snider.converter.Mapper;
import com.ted.snider.converter.MapperConfiguration;
import com.ted.snider.converter.properties.MappingProp;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLFile {
	private Map<String, Mapper> props;
	private File input;

	public XMLFile(String infile, List<MappingProp> properties) throws Exception {
		this.props = new MapperConfiguration().configure(properties);
		this.input = new File(infile);
	}

	protected XMLFile() {
		// Useful for testing. Make sure tests are in the same package.
	}

	public String applyMapping() throws InvocationTargetException, IllegalStateException, DOMException {
		List<String> nodes = new LinkedList<String>();
		Document xmlRecords = parseDocument(input);
		Element root = xmlRecords.getDocumentElement();
		addNode(root, nodes);
		return String.join(" ", nodes);
	}

	private void addNode(Node node, List<String> preJSON)
			throws InvocationTargetException, IllegalStateException, DOMException {
		// if this is not a leaf/data node
		if (node.hasChildNodes()) {
			boolean array = isList(node);
			preJSON.add((array) ? "[" : "{");

			// Add all child nodes, separated by commas
			List<String> children = new LinkedList<>();
			NodeList childNodes = node.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				addNode(childNodes.item(i), children);
			}
			String.join(",", children);

			preJSON.add((array) ? "]" : "}");
		} else {
			// find the mapping property for this node name
			Mapper mapping = props.get(node.getNodeName());
			preJSON.add(mapping.map(node.getTextContent()));
		}
	}

	protected Document parseDocument(File raw) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		Document doc;
		try {
			doc = dBuilder.parse(raw);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}
		doc.getDocumentElement().normalize();
		return doc;
	}

	protected boolean isList(Node node) {
		int children = node.getChildNodes().getLength();
		int firstNames = ((Element) node).getElementsByTagName(node.getFirstChild().getNodeName()).getLength();

		return children == firstNames;
	}
}
