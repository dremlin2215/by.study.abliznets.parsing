package validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XMLValidator {
    private static final Logger LOGGER = LogManager.getLogger(XMLValidator.class);

    public static boolean validate(InputStream XMLPath) {

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        String path = XMLValidator.class.getResource("/devices.xsd").getPath();
        File schemaFile = new File(path);
        try {

            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(XMLPath));
        } catch (SAXException | IOException e) {
            LOGGER.info("File validation - negative");
            return false;
        }
        LOGGER.info("File validation - positive");
        return true;

    }
}
