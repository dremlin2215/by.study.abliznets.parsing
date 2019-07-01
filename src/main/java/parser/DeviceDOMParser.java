package parser;

import beans.*;
import exception.ParseDateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DeviceDOMParser extends AbstractDeviceParser {
    private static final Logger LOGGER = LogManager.getLogger(DeviceDOMParser.class);
    private List<Device> deviceList;
    private DocumentBuilder documentBuilder;

    public DeviceDOMParser() {
        this.deviceList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            this.documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.warn("ParserConfigurationException", e);
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        if (element.getElementsByTagName(elementName).item(0) != null) {
            NodeList nodelistList = element.getElementsByTagName(elementName).item(0).getChildNodes();
            if ((nodelistList.getLength() == 0))
                return "";
            Node nValue = nodelistList.item(0);
            return nValue.getNodeValue();
        }
        return "";
    }

    public static Date parseDate(String text) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(text);
        } catch (ParseException e) {
            throw new ParseDateException();
        }
        return date;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void buildDeviceList(InputStream stream) {
        Document doc;
        try {
            doc = documentBuilder.parse(stream);
            Element root = doc.getDocumentElement();
            NodeList obligatoryDeviceNodes = root.getElementsByTagName("obligatory_device");
            NodeList nonobligatoryDeviceNodes = root.getElementsByTagName("nonobligatory_device");

            for (int i = 0; i < obligatoryDeviceNodes.getLength(); i++) {
                Element deviceElement = (Element) obligatoryDeviceNodes.item(i);
                Device obligatoryDevice = buildObligatoryDevice(deviceElement);
                deviceList.add(obligatoryDevice);

            }
            for (int i = 0; i < nonobligatoryDeviceNodes.getLength(); i++) {
                Element deviceElement = (Element) nonobligatoryDeviceNodes.item(i);
                Device nonobligatoryDevice = buildNonobligatoryDevice(deviceElement);
                deviceList.add(nonobligatoryDevice);
            }
        } catch (SAXException | IOException e) {
            LOGGER.warn("Exception in method buildDeviceList of DeviceDomParser Class", e);
        }
    }

    private ObligatoryDevice buildObligatoryDevice(Element element) {
        ObligatoryDevice device =
                (ObligatoryDevice) buildAbstractDevice(element, new ObligatoryDevice());
        device.setObligatory(Boolean.getBoolean(getElementTextContent(element, "isObligatory")));
        device.setType(ObligatoryTypes.valueOf(getElementTextContent(element, "type").toUpperCase()));
        device.setFrequency(getElementTextContent(element, "frequency"));
        device.setRAMType(getElementTextContent(element, "RAMType"));
        device.setSocket(getElementTextContent(element, "socket"));
        device.setChipset(getElementTextContent(element, "chipset"));
        device.setInterface_(getElementTextContent(element, "interface"));
        device.setVolume(getElementTextContent(element, "volume"));

        return device;
    }

    private NonobligatoryDevice buildNonobligatoryDevice(Element element) {
        NonobligatoryDevice device =
                (NonobligatoryDevice) buildAbstractDevice(element, new NonobligatoryDevice());
        device.setObligatory(Boolean.getBoolean(getElementTextContent(element, "isObligatory")));
        device.setType(NonobligatoryTypes
                .valueOf(getElementTextContent(element, "type").toUpperCase()));
        device.setParameters(getElementTextContent(element, "parameters"));
        device.setPeripheral(Boolean.parseBoolean(getElementTextContent(element, "isPeripheral")));

        return device;
    }

    private Device buildAbstractDevice(Element element, Device device) {
        device.setId(element.getAttribute("id"));
        device.setName(getElementTextContent(element, "name"));
        Shipment shipment = buildShipment((Element) element.getElementsByTagName("shipment").item(0));
        device.setShipment(shipment);
        device.setHasCooler(Boolean.getBoolean(getElementTextContent(element, "hasCooler")));
        device.setPorts(getElementTextContent(element, "ports"));
        device.setEnergyConsumption(Double
                .parseDouble(getElementTextContent(element, "energyConsumption")));
        return device;
    }

    private Shipment buildShipment(Element element) {
        Shipment shipment = new Shipment();
        shipment.setCompany(getElementTextContent(element, "company"));
        shipment.setDate(parseDate(getElementTextContent(element, "shipmentDate")));
        shipment.setPrice(Double.parseDouble(getElementTextContent(element, "price")));
        return shipment;
    }

}
