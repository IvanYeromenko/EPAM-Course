package com.epam.rd.java.basic.practice7.controller;

import com.epam.rd.java.basic.practice7.constants.XMLConstants;
import com.epam.rd.java.basic.practice7.constants.XMLTariffConstants;
import com.epam.rd.java.basic.practice7.tariff.CallPrice;
import com.epam.rd.java.basic.practice7.tariff.Parameters;
import com.epam.rd.java.basic.practice7.tariff.Tariff;
import com.epam.rd.java.basic.practice7.util.Util;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SAXController extends DefaultHandler {

    private static class ParserBooleanContainer {
        static boolean tariffName;
        private static boolean operatorName;
        private static boolean payroll;
        private static boolean callPrice;
        private static boolean inNetworkCall;
        private static boolean outNetworkCall;
        private static boolean lanadLineCall;
        private static boolean smsPrice;
        private static boolean parameters;
        private static boolean lovelyNumberExistance;
        private static boolean tariffication;
        private static boolean connectingPrice;

    }

    private final String xmlFilePath;
    private final List<Tariff> tariffs;
    private Tariff tariff;
    private Parameters parameters;
    private CallPrice callPrice;

    public SAXController(String xmlFlePath) {
        this.xmlFilePath = xmlFlePath;
        tariffs = new ArrayList<>();
    }

    public void parse(boolean validate) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);

        if (validate) {
            factory.setValidating(true);
            try {
                factory.setFeature(XMLConstants.FEATURE_TURN_VALIDATION_ON, true);
                factory.setFeature(XMLConstants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
            } catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
        javax.xml.parsers.SAXParser parser;
        try {
            parser = factory.newSAXParser();
            parser.parse(xmlFilePath, this);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case XMLTariffConstants.TARIFF:
                tariff = new Tariff();
                break;
            case XMLTariffConstants.TARIFF_NAME:
                ParserBooleanContainer.tariffName = true;
                break;
            case XMLTariffConstants.OPERATOR_NAME:
                ParserBooleanContainer.operatorName = true;
                break;
            case XMLTariffConstants.PAYROLL:
                ParserBooleanContainer.payroll = true;
                break;
            case XMLTariffConstants.CALL_PRICE:
                callPrice = new CallPrice();
                ParserBooleanContainer.callPrice = true;
                break;
            case XMLTariffConstants.WITHIN_NETWORK_CALL_PRICE:
                ParserBooleanContainer.inNetworkCall = true;
                break;
            case XMLTariffConstants.OUT_OF_NETWORK_CALL:
                ParserBooleanContainer.outNetworkCall = true;
                break;
            case XMLTariffConstants.LAND_LINE_NUM_CALL_PRICE:
                ParserBooleanContainer.lanadLineCall = true;
                break;
            case XMLTariffConstants.SMS_PRICE:
                ParserBooleanContainer.smsPrice = true;
                break;
            case XMLTariffConstants.PARAMETERS:
                parameters = new Parameters();
                ParserBooleanContainer.parameters = true;
                break;
            case XMLTariffConstants.LOVELY_NUMBER_EXISTANCE:
                ParserBooleanContainer.lovelyNumberExistance = true;
                break;
            case XMLTariffConstants.TARIFFICATION:
                ParserBooleanContainer.tariffication = true;
                break;
            case XMLTariffConstants.CONNECTION_PRICE:
                ParserBooleanContainer.connectingPrice = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case XMLTariffConstants.TARIFF:
                if (!Objects.isNull(tariff)) {
                    tariffs.add(tariff);
                }

                break;
            case XMLTariffConstants.TARIFF_NAME:
                ParserBooleanContainer.tariffName = false;
                break;
            case XMLTariffConstants.OPERATOR_NAME:
                ParserBooleanContainer.operatorName = false;
                break;
            case XMLTariffConstants.PAYROLL:
                ParserBooleanContainer.payroll = false;
                break;
            case XMLTariffConstants.CALL_PRICE:
                tariff.setCallPrices(callPrice);
                ParserBooleanContainer.callPrice = false;
                break;
            case XMLTariffConstants.WITHIN_NETWORK_CALL_PRICE:
                ParserBooleanContainer.inNetworkCall = false;
                break;
            case XMLTariffConstants.OUT_OF_NETWORK_CALL:
                ParserBooleanContainer.outNetworkCall = false;
                break;
            case XMLTariffConstants.LAND_LINE_NUM_CALL_PRICE:
                ParserBooleanContainer.lanadLineCall = false;
                break;
            case XMLTariffConstants.SMS_PRICE: ParserBooleanContainer.smsPrice = false;
                break;
            case XMLTariffConstants.PARAMETERS:
                tariff.setParameters(parameters);
                ParserBooleanContainer.parameters = false;
                break;
            case XMLTariffConstants.LOVELY_NUMBER_EXISTANCE:
                ParserBooleanContainer.lovelyNumberExistance = false;
                break;
            case XMLTariffConstants.TARIFFICATION:
                ParserBooleanContainer.tariffication = false;
                break;
            case XMLTariffConstants.CONNECTION_PRICE:
                ParserBooleanContainer.connectingPrice = false;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (!value.isEmpty()) {
            if (ParserBooleanContainer.tariffName) {
                tariff.setTariffName(value);
            } else if (ParserBooleanContainer.operatorName) {
                tariff.setOperatorName(value);
            } else if (ParserBooleanContainer.payroll) {
                tariff.setPayroll(Double.parseDouble(value));
            } else if (ParserBooleanContainer.callPrice) {
                if (!setCallPrice(value)) {
                    callPrice = new CallPrice();
                }
            } else if (ParserBooleanContainer.smsPrice) {
                tariff.setSmsPrice(Double.parseDouble(value));
            } else if (ParserBooleanContainer.parameters) {
                if (!setParameters(value)) {
                    parameters = new Parameters();
                }
            }
        }
    }

    private boolean setParameters(String s) {

        if (ParserBooleanContainer.lovelyNumberExistance) {
            parameters.setLovelyNumberExitance(Integer.parseInt(s));
            return true;
        } else if (ParserBooleanContainer.tariffication) {
            parameters.setTariffication(Integer.parseInt(s));
            return true;
        } else if (ParserBooleanContainer.connectingPrice) {
            parameters.setConnectingPrice(Double.parseDouble(s));
            return true;
        }
        return false;
    }

    private boolean setCallPrice(String s) {
        if (ParserBooleanContainer.inNetworkCall) {
            callPrice.setWithinNetworkCallPrice(Double.parseDouble(s));
            return true;
        } else if (ParserBooleanContainer.outNetworkCall) {
            callPrice.setOutOfNetworkCallPrice(Double.parseDouble(s));
            return true;
        } else if (ParserBooleanContainer.lanadLineCall) {
            callPrice.setLandLineNumCallPrice(Double.parseDouble(s));
            return true;
        }
        return false;
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

    public void writeXML() {
        tariffs.sort(Comparator.reverseOrder());
        try {
            Util.writeSaxStax(tariffs, XMLConstants.SAX_RESULT);
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SAXController saxParser = new SAXController("input.xml");
        saxParser.parse(true);
        saxParser.writeXML();
    }
}
