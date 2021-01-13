package com.epam.rd.java.basic.practice7.example.controller;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;

import com.epam.rd.java.basic.practice7.example.constants.Constants;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.rd.java.basic.practice7.example.constants.XML;
import com.epam.rd.java.basic.practice7.example.entity.Answer;
import com.epam.rd.java.basic.practice7.example.entity.Question;
import com.epam.rd.java.basic.practice7.example.entity.Test;

public class STAXController extends DefaultHandler {

	private final String xmlFileName;

	private Test test;

	public Test getTest() {
		return test;
	}

	public STAXController(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public void parse() throws XMLStreamException {

		Question question = null;
		Answer answer = null;

		String currentElement = null;
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

		XMLEventReader reader = factory.createXMLEventReader(
				new StreamSource(xmlFileName));

		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();

			if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
				continue;
			}

			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				currentElement = startElement.getName().getLocalPart();

				if (XML.TEST.equalsTo(currentElement)) {
					test = new Test();
					continue;
				}

				if (XML.QUESTION.equalsTo(currentElement)) {
					question = new Question();
					continue;
				}

				if (XML.ANSWER.equalsTo(currentElement)) {
					answer = new Answer();
					Attribute attribute = startElement.getAttributeByName(
							new QName(XML.CORRECT.value()));
					if (attribute != null) {
						answer.setCorrect(Boolean.parseBoolean(attribute.getValue()));
					}
				}
			}

			if (event.isCharacters()) {
				Characters characters = event.asCharacters();

				if (XML.QUESTION_TEXT.equalsTo(currentElement)) {
					assert question != null;
					question.setQuestionText(characters.getData());
					continue;
				}

				if (XML.ANSWER.equalsTo(currentElement)) {
					assert answer != null;
					answer.setContent(characters.getData());
					continue;
				}
			}

			if (event.isEndElement()) {
				EndElement endElement = event.asEndElement();
				String localName = endElement.getName().getLocalPart();

				if (XML.QUESTION.equalsTo(localName)) {
					test.getQuestions().add(question);
					continue;
				}

				if (XML.ANSWER.equalsTo(localName)) {
					assert question != null;
					question.getAnswers().add(answer);
				}
			}
		}
		reader.close();
	}

	public static void main(String[] args) throws Exception {

		STAXController staxContr = new STAXController(Constants.VALID_XML_FILE);
		staxContr.parse();

		Test test = staxContr.getTest();

		System.out.print("Here is the test: \n" + test);
	}
}