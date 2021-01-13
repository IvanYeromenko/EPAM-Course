package com.epam.rd.java.basic.practice7.example.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.epam.rd.java.basic.practice7.example.constants.Constants;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.rd.java.basic.practice7.example.constants.XML;
import com.epam.rd.java.basic.practice7.example.entity.Answer;
import com.epam.rd.java.basic.practice7.example.entity.Question;
import com.epam.rd.java.basic.practice7.example.entity.Test;

public class SAXController extends DefaultHandler {
	
	private String xmlFileName;

	private String currentElement;

	private Test test;
	
	private Question question;
	
	private Answer answer;

	public SAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse(boolean validate) 
			throws ParserConfigurationException, SAXException, IOException {

		SAXParserFactory factory = SAXParserFactory.newInstance();

		factory.setNamespaceAware(true);

		if (validate) {
			factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
			factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
		}

		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlFileName, this);
	}

	@Override
	public void error(org.xml.sax.SAXParseException e) throws SAXException {
		throw e;
	}

	public Test getTest() {
		return test;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		currentElement = localName;

		if (XML.TEST.equalsTo(currentElement)) {
			test = new Test();
			return;
		}

		if (XML.QUESTION.equalsTo(currentElement)) {
			question = new Question();
			return;
		}

		if (XML.ANSWER.equalsTo(currentElement)) {
			answer = new Answer();
			if (attributes.getLength() > 0) {
				answer.setCorrect(Boolean.parseBoolean(attributes.getValue(uri,
						XML.CORRECT.value())));
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		String elementText = new String(ch, start, length).trim();

		if (elementText.isEmpty()) { 
			return;
		}

		if (XML.QUESTION_TEXT.equalsTo(currentElement)) {
			question.setQuestionText(elementText);
			return;
		}

		if (XML.ANSWER.equalsTo(currentElement)) {
			answer.setContent(elementText);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (XML.QUESTION.equalsTo(localName)) {
			test.getQuestions().add(question);
			return;
		}

		if (XML.ANSWER.equalsTo(localName)) {
			question.getAnswers().add(answer);
		}
	}

	public static void main(String[] args) throws Exception {

		SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);

		saxContr.parse(true);

		Test test = saxContr.getTest();

		System.out.print("Here is the test: \n" + test);

		saxContr = new SAXController(Constants.INVALID_XML_FILE);
		try {
			saxContr.parse(true);
		} catch (Exception ex) {
			System.err.println("Validation is failed:\n" + ex.getMessage());
			System.err
					.println("Try to print test object:" + saxContr.getTest());
		}

		saxContr.parse(false);		

		System.out.print("Here is the test: \n" + saxContr.getTest());
	}
}