package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class DeviceSAXParser extends AbstractDeviceParser {

    private static final Logger LOGGER = LogManager.getLogger(DeviceSAXParser.class);
    private DeviceSAXHandler handler;
    private XMLReader reader;

    public DeviceSAXParser() {
        handler = new DeviceSAXHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOGGER.warn("SAXException", e);
        }
    }

    @Override
    public void buildDeviceList(InputStream inputStream) throws ParserConfigurationException {
        try {
            reader.parse(new InputSource(inputStream));
        } catch (SAXException e) {
            LOGGER.warn("SAXException", e);
        } catch (IOException e) {
            LOGGER.warn("IOException", e);
        }
        deviceList = handler.getDeviceList(); //!!!
    }
}