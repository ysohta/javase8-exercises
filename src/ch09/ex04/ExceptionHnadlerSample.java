package ch09.ex04;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by yukiohta on 2015/11/16.
 */
public class ExceptionHnadlerSample {
    public void catchMultiExceptions() {
        String filename = "sample.xml";
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = parserFactor.newSAXParser();
            parser.parse(filename, new SaxHandler());

            // catch multiple exceptions instead of Exception class
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void catchSuperException() {
        String className = "java.lang.String";
        String text = "text";
        String methodName = "toString";
        try {
            Class clazz = Class.forName(className);
            Class[] params = {String.class};
            Constructor constructor = clazz.getConstructor(params);
            String obj = (String) constructor.newInstance(text);
            Method toStringMethod = clazz.getDeclaredMethod(methodName);
            System.out.println(toStringMethod.invoke(obj));

            // catch super class instead of the classes commented out
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }
    }

    class SaxHandler extends DefaultHandler {
        public void startDocument() throws SAXException {
        }

        public void endDocument() throws SAXException {
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
        }

        public void characters(char ch[], int start, int length) throws SAXException {
        }
    }
}
