package factory;

import beans.ParserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import parser.AbstractDeviceParser;
import parser.DeviceDOMParser;
import parser.DeviceSAXParser;
import parser.DeviceStAXParser;

public enum ParserFactory {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(ParserFactory.class);

    public AbstractDeviceParser createParser(String parserType) {
        ParserType chosenParser = ParserType.valueOf(parserType.toUpperCase());

        switch (chosenParser) {
            case DOM:
                LOGGER.info("DOM parser chosen");
                return new DeviceDOMParser();
            case SAX:
                LOGGER.info("SAX parser chosen");
                return new DeviceSAXParser();
            case STAX:
                LOGGER.info("StAX parser chosen");
                return new DeviceStAXParser();
            default:
                LOGGER.info("Default parser chosen");
                return new DeviceDOMParser();

        }

    }
}
