package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.controller.DOMController;
import com.epam.rd.java.basic.practice7.controller.SAXController;
import com.epam.rd.java.basic.practice7.controller.STAXController;

public class Main {

    public static void main(String[] args) throws Exception {

        DOMController domController = new DOMController(args[0]);
        domController.parseXML(true);
        domController.saveToXML();

        STAXController stAXController = new STAXController(args[0]);
        stAXController.parse();
        stAXController.writeXML();

        SAXController saxController = new SAXController(args[0]);
        saxController.parse(true);
        saxController.writeXML();

    }
}
