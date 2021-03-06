package se.atg.service.harrykart.rest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//import com.jcabi.xml.XML;
//import com.jcabi.xml.XMLDocument;
public class XmlAsStringInJava { public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

    // our XML file for this example
    File xmlFile = new File("info.xml");

    // Let's get XML file as String using BufferedReader
    // FileReader uses platform's default character encoding
    // if you need to specify a different encoding, use InputStreamReader
    Reader fileReader = new FileReader(xmlFile);
    BufferedReader bufReader = new BufferedReader(fileReader);

    StringBuilder sb = new StringBuilder();
    String line = bufReader.readLine();
    while( line != null){
        sb.append(line).append("\n");
        line = bufReader.readLine();
    }
    String xml2String = sb.toString();
    System.out.println("XML to String using BufferedReader : ");
    System.out.println(xml2String);

    bufReader.close();

    // parsing XML file to get as String using DOM Parser
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
    Document xmlDom = docBuilder.parse(xmlFile);

    String xmlAsString = xmlDom.toString(); // this will not print what you want
    System.out.println("XML as String using DOM Parser : ");
    System.out.println(xmlAsString);


    // Reading XML as String using jCabi library
    //XML xml = new XMLDocument(new File("info.xml"));
    //String xmlString = xml.toString();
   // System.out.println("XML as String using JCabi library : " );
    //System.out.println(xmlString);
}
}