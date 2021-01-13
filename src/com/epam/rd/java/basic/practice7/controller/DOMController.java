package com.epam.rd.java.basic.practice7.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.rd.java.basic.practice7.constants.XMLConstants;
import com.epam.rd.java.basic.practice7.constants.XMLTariffConstants;
import com.epam.rd.java.basic.practice7.tariff.CallPrice;
import com.epam.rd.java.basic.practice7.tariff.Parameters;
import com.epam.rd.java.basic.practice7.tariff.Tariff;
import com.epam.rd.java.basic.practice7.util.Util;

public class DOMController {
    private String xmlFilePath;
    private Document xmlDocument;
    private List<Tariff> tariffs;
    private Tariff tariff;

    public DOMController(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
        tariffs = new ArrayList<>();
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void parseXML(boolean validate) {
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        dFactory.setNamespaceAware(true);
        if (validate) {
            try {
                dFactory.setFeature("http://xml.org/sax/features/validation", true);
                dFactory.setFeature("http://apache.org/xml/features/validation/schema", true);
            } catch (ParserConfigurationException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        try {
            xmlDocument = dFactory.newDocumentBuilder().parse(xmlFilePath);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println(e.getLocalizedMessage());
        }
        NodeList operators = xmlDocument.getElementsByTagName("tariff");
        for (int i = 0; i < operators.getLength(); i++) {
            NodeList operator = operators.item(i).getChildNodes();
            parse(operator);
            tariffs.add(tariff);

        }

    }

    private void parse(NodeList operator) {
        tariff = new Tariff();
        Node node;
        for (int i = 0; i < operator.getLength(); i++) {
            node = operator.item(i);
            if (node.getNodeName().equals(XMLTariffConstants.TARIFF_NAME)) {
                tariff.setTariffName(node.getTextContent());
            } else if (node.getNodeName().equals(XMLTariffConstants.OPERATOR_NAME)) {
                tariff.setOperatorName(node.getTextContent());
            } else if (node.getNodeName().equals(XMLTariffConstants.PAYROLL)) {
                tariff.setPayroll(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(XMLTariffConstants.CALL_PRICE)) {
                tariff.setCallPrices(getCallPrice(node.getChildNodes()));
            } else if (node.getNodeName().equals(XMLTariffConstants.SMS_PRICE)) {
                tariff.setSmsPrice(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(XMLTariffConstants.PARAMETERS)) {
                tariff.setParameters(getParameters(node.getChildNodes()));
            }
        }
    }

    private static CallPrice getCallPrice(NodeList nodeList) {
        CallPrice callPrice = new CallPrice();
        Node node;
        for (int i = 0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            if (node.getNodeName().equals(XMLTariffConstants.WITHIN_NETWORK_CALL_PRICE)) {
                callPrice.setWithinNetworkCallPrice(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(XMLTariffConstants.OUT_OF_NETWORK_CALL)) {
                callPrice.setOutOfNetworkCallPrice(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(XMLTariffConstants.LAND_LINE_NUM_CALL_PRICE)) {
                callPrice.setLandLineNumCallPrice(Double.parseDouble(node.getTextContent()));
            }
        }
        return callPrice;
    }

    private static Parameters getParameters(NodeList nodeList) {
        Parameters parameters = new Parameters();
        Node node;
        for (int i = 0; i < nodeList.getLength(); i++) {
            node = nodeList.item(i);
            if (node.getNodeName().equals(XMLTariffConstants.LOVELY_NUMBER_EXISTANCE)) {
                parameters.setLovelyNumberExitance(Integer.parseInt(node.getTextContent()));
            } else if (node.getNodeName().equals(XMLTariffConstants.CONNECTION_PRICE)) {
                parameters.setConnectingPrice(Double.parseDouble(node.getTextContent()));
            } else if (node.getNodeName().equals(XMLTariffConstants.TARIFFICATION)) {
                parameters.setTariffication(Integer.parseInt(node.getTextContent()));
            }
        }
        return parameters;
    }

    private Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        builderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement(XMLTariffConstants.TARIFFS);
        root.setAttribute("xmlns:tns", "http://www.example.org/input");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:schemaLocation", "http://www.example.org/input input.xsd");
        document.appendChild(root);
        Element element;
        for (Tariff tariffObj : tariffs) {
            Element tariffElement = document.createElement(XMLTariffConstants.TARIFF);
            List<Object> listedTariff = Util.tariffToList(tariffObj);
            List<Object> listedCallPrice = listedTariff.subList(4, 7);
            List<Object> listedParameters = listedTariff.subList(7, listedTariff.size());
            for (int i = 0; i < 4; i++) {
                element = document.createElement(XMLTariffConstants.TARIFF_ARRAY[i]);
                element.setTextContent(listedTariff.get(i).toString());
                tariffElement.appendChild(element);
            }
            tariffElement.appendChild(getCallPrice(document, listedCallPrice));
            tariffElement.appendChild(getParameters(document, listedParameters));
            root.appendChild(tariffElement);
        }
        return document;

    }

    private static Element getCallPrice(Document document, List<Object> callPrice) {
        Element callPriceElement = document.createElement(XMLTariffConstants.CALL_PRICE);
        Element childElement;
        for (int i = 1; i <= XMLTariffConstants.CALL_PRICE_ARRAY.length; i++) {
            childElement = document.createElement(XMLTariffConstants.CALL_PRICE_ARRAY[i - 1]);
            childElement.setTextContent(callPrice.get(i - 1).toString());
            callPriceElement.appendChild(childElement);
        }
        return callPriceElement;
    }

    private static Element getParameters(Document document, List<Object> parameters) {
        Element parametersElement = document.createElement(XMLTariffConstants.PARAMETERS);
        Element childElement;
        for (int i = 1; i <= XMLTariffConstants.PARAMETERS_ARRAY.length; i++) {
            childElement = document.createElement(XMLTariffConstants.PARAMETERS_ARRAY[i - 1]);
            childElement.setTextContent(parameters.get(i - 1).toString());
            parametersElement.appendChild(childElement);
        }
        return parametersElement;
    }

    public void saveToXML() throws TransformerException, ParserConfigurationException {

        StreamResult result = new StreamResult(new File(XMLConstants.DOM_RESULT));

        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        t.transform(new DOMSource(createDocument()), result);
    }

    public static void main(String[] args) throws TransformerException, ParserConfigurationException {
        DOMController parser = new DOMController("input.xml");
        parser.parseXML(true);
        parser.saveToXML();
    }
}
