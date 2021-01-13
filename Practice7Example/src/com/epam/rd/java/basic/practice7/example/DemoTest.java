package com.epam.rd.java.basic.practice7.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DemoTest {

    @Before
    public void create() {
        assertFalse(Files.exists(Paths.get("output.dom.xml")));
        assertFalse(Files.exists(Paths.get("output.sax.xml")));
        assertFalse(Files.exists(Paths.get("output.stax.xml")));
    }

    @Test
    public void main() throws Exception {
        Demo.main(new String[]{"input.xml","input.xsd"});
        Main.main(new String[]{"input.xml","input.xsd"});
        Main.main(new String[]{"input.xml","input.xsd","input.xml","input.xsd"});
        assertEquals("Demo",Demo.class.getSimpleName());
    }
    @After
    public void clean() throws IOException {
        Path pathDom = Paths.get("output.dom.xml");
        Path pathSax = Paths.get("output.sax.xml");
        Path pathStax = Paths.get("output.stax.xml");
        Files.deleteIfExists(pathDom);
        Files.deleteIfExists(pathSax);
        Files.deleteIfExists(pathStax);
    }
}