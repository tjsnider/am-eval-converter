package com.ted.snider.converter.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.ted.snider.converter.properties.MappingProp;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLFileTest {
    DocumentBuilder builder;

    @BeforeEach
    public void setUp() throws ParserConfigurationException {
    }

    @Test
    @DisplayName("Test that isList can identify a list.")
    public void testIsListPositive() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        this.builder = factory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append("<?xml version=\"1.0\"?><root><child/><child/><child /></root>");
        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);
        Element root = doc.getDocumentElement();

        boolean result = new XMLFile().isList(root);

        assertTrue(result, "isList failed to identify a list node.");
    }

    @Test
    @DisplayName("Test that isList can identify a non-list object.")
    public void testIsListNegative() throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        this.builder = factory.newDocumentBuilder();
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append("<?xml version=\"1.0\"?><root><tom/><dick/><harry /></root>");
        ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
        Document doc = builder.parse(input);
        Element root = doc.getDocumentElement();

        boolean result = new XMLFile().isList(root);

        assertFalse(result, "isList failed to identify a non-list node.");
    }

    @Test
    @DisplayName("Test that parseDocument correctly returns the root node.")
    public void testParseDocument() {
        File input = new File("src\\test\\resources\\input.xml");
        Document doc = new XMLFile().parseDocument(input);

        assertEquals("patients", doc.getDocumentElement().getNodeName());
    }

    @Test
    @DisplayName("Test that applyMapping works.")
    public void testApplyMapping() throws Exception {
        String input = "src/test/resources/input.xml";
        File expectedFile = new File("src/test/resources/expected.json");
        String mapping = "src/test/resources/mapping.json";

        List<MappingProp> properties = new MappingFile(mapping).read();
        XMLFile source = new XMLFile(input, properties);
        String output = source.applyMapping();

        JSONObject expected = new JSONObject(readStringFromFile(new FileInputStream(expectedFile)));
        JSONObject actual = new JSONObject(output);

        assertEquals(expected.toString(4), actual.toString(4));
    }

    private String readStringFromFile(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
          = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}