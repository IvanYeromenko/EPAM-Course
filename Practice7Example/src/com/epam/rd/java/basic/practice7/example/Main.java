package com.epam.rd.java.basic.practice7.example;

import com.epam.rd.java.basic.practice7.example.controller.DOMController;
import com.epam.rd.java.basic.practice7.example.controller.SAXController;
import com.epam.rd.java.basic.practice7.example.controller.STAXController;
import com.epam.rd.java.basic.practice7.example.entity.Test;
import com.epam.rd.java.basic.practice7.example.util.Sorter;

public class Main {
	/*public static void usage() {
		System.out.println("java ua.nure.kolesnikov.practice7.Main xmlFileName");
	}*/
	
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			//usage();
			return;
		}
		
		String xmlFileName = args[0];
		System.out.println("Input ==> " + xmlFileName);

		DOMController domController = new DOMController(xmlFileName);
		domController.parse(true);
		Test test = domController.getTest();

		Sorter.sortQuestionsByQuestionText(test);
		String o = "Output ==> ";
		String outputXmlFile = "output.dom.xml";
		DOMController.saveToXML(test, outputXmlFile);
		System.out.println(o + outputXmlFile);

		SAXController saxController = new SAXController(xmlFileName);
		saxController.parse(true);
		test = saxController.getTest();

		Sorter.sortQuestionsByAnswersNumber(test);

		outputXmlFile = "output.sax.xml";

		DOMController.saveToXML(test, outputXmlFile);
		System.out.println(o + outputXmlFile);

		STAXController staxController = new STAXController(xmlFileName);
		staxController.parse();
		test = staxController.getTest();

		Sorter.sortAnswersByContent(test);

		outputXmlFile = "output.stax.xml";
		DOMController.saveToXML(test, outputXmlFile);
		System.out.println(o + outputXmlFile);
	}

}