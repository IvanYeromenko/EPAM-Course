package com.epam.rd.java.basic.practice7.example.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.epam.rd.java.basic.practice7.example.constants.Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.rd.java.basic.practice7.example.constants.XML;
import com.epam.rd.java.basic.practice7.example.entity.Answer;
import com.epam.rd.java.basic.practice7.example.entity.Question;
import com.epam.rd.java.basic.practice7.example.entity.Test;


public class DOMController {

	private String xmlFileName;

	private Test test;

	public DOMController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public Test getTest() {
		return test;
	}

	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		if (validate) {
			dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

			dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		DocumentBuilder db = dbf.newDocumentBuilder();

		db.setErrorHandler(new DefaultHandler() {
			@Override
			public void error(SAXParseException e) throws SAXException {
				throw e;
			}
		});

		Document document = db.parse(xmlFileName);

		Element root = document.getDocumentElement();

		test = new Test();

		NodeList questionNodes = root
				.getElementsByTagName(XML.QUESTION.value());

		for (int j = 0; j < questionNodes.getLength(); j++) {
			Question question = getQuestion(questionNodes.item(j));
			test.getQuestions().add(question);
		}
	}

	private static Question getQuestion(Node qNode) {
		Question question = new Question();
		Element qElement = (Element) qNode;

		Node qtNode = qElement.getElementsByTagName(XML.QUESTION_TEXT.value()).item(0);
		question.setQuestionText(qtNode.getTextContent());

		NodeList aNodeList = qElement.getElementsByTagName(XML.ANSWER.value());
		for (int j = 0; j < aNodeList.getLength(); j++) {
			Answer answer = getAnswer(aNodeList.item(j));

			question.getAnswers().add(answer);
		}

		return question;
	}

	private static Answer getAnswer(Node aNode) {
		Answer answer = new Answer();
		Element aElement = (Element) aNode;

		String correct = aElement.getAttribute(XML.CORRECT.value());
		answer.setCorrect(Boolean.valueOf(correct));

		String content = aElement.getTextContent();
		answer.setContent(content);

		return answer;
	}

	public static Document getDocument(Test test) throws ParserConfigurationException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.newDocument();

		Element tElement = document.createElement(XML.TEST.value());

		document.appendChild(tElement);

		for (Question question : test.getQuestions()) {

			Element qElement = document.createElement(XML.QUESTION.value());
			tElement.appendChild(qElement);

			Element qtElement = 
					document.createElement(XML.QUESTION_TEXT.value());
			qtElement.setTextContent(question.getQuestionText());
			qElement.appendChild(qtElement);

			for (Answer answer : question.getAnswers()) {
				Element aElement = document.createElement(XML.ANSWER.value());
				aElement.setTextContent(answer.getContent());

				if (answer.isCorrect()) {
					aElement.setAttribute(XML.CORRECT.value(), "true");
				}
				qElement.appendChild(aElement);
			}
		}

		return document;		
	}

	public static void saveToXML(Test test, String xmlFileName)
			throws ParserConfigurationException, TransformerException {
		saveToXML(getDocument(test), xmlFileName);		
	}

	public static void saveToXML(Document document, String xmlFileName) 
			throws TransformerException {
		
		StreamResult result = new StreamResult(new File(xmlFileName));

		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");			

		t.transform(new DOMSource(document), result);
	}

	public static void main(String[] args) throws Exception {

		DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);
		try {
			domContr.parse(true);
		} catch (SAXException ex) {
			System.err.println("XML not valid");
			System.err.println("Test object --> " + domContr.getTest());
		}

		domContr.parse(false);

		System.out.print("Here is the test: \n" + domContr.getTest());

		Test test = domContr.getTest();
		DOMController.saveToXML(test, Constants.INVALID_XML_FILE + ".dom-result.xml");
	}
}
