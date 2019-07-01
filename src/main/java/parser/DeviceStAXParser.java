package parser;

import beans.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

public class DeviceStAXParser extends AbstractDeviceParser {
    private static final Logger LOGGER = LogManager.getLogger(DeviceStAXParser.class);
    private XMLInputFactory inputFactory;

    public DeviceStAXParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public void buildDeviceList(InputStream inputStream) {
        XMLStreamReader reader;
        String name;

        try {
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(DeviceParserEnum.OBLIGATORY_DEVICE.getValue())) {
                        Device obligatoryDevice = buildDevice(new ObligatoryDevice(), reader);
                        deviceList.add(obligatoryDevice);
                    } else if (name.equals(DeviceParserEnum.NONOBLIGATORY_DEVICE.getValue())) {
                        Device nonobligatoryDevice = buildDevice(new NonobligatoryDevice(), reader);
                        deviceList.add(nonobligatoryDevice);
                    }
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.info("XMLStreamException in DeviceStAXParser class in buildDeviceList method");
        }
    }

    private Device buildDevice(Device device, XMLStreamReader reader) {
        device.setId(reader.getAttributeValue(null, DeviceParserEnum.ID.getValue()));
        //
        String element;
        DeviceParserEnum deviceParserEnum;

        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        element = reader.getLocalName();
                        deviceParserEnum = DeviceParserEnum.valueOf(element.toUpperCase());

                        switch (deviceParserEnum) {
                            case NAME:
                                device.setName(getXMLText(reader));
                                break;
                            case SHIPMENT:
                                device.setShipment(buildShipment(reader));
                                break;
                            case ISOBLIGATORY:
                                if (device instanceof ObligatoryDevice) {
                                    ((ObligatoryDevice) device).setObligatory(true);
                                } else {
                                    ((NonobligatoryDevice) device).setObligatory(false);
                                }
                                break;
                            case HASCOOLER:
                                device.setHasCooler(Boolean.parseBoolean(getXMLText(reader)));
                                break;
                            case PORTS:
                                device.setPorts(getXMLText(reader));
                                break;
                            case ENERGYCONSUMPTION:
                                device.setEnergyConsumption(Double.parseDouble(getXMLText(reader)));
                                break;
                            case TYPE:
                                if (device instanceof ObligatoryDevice) {
                                    ((ObligatoryDevice) device).setType(ObligatoryTypes
                                            .valueOf((getXMLText(reader)).toUpperCase()));
                                } else if (device instanceof NonobligatoryDevice) {
                                    ((NonobligatoryDevice) device).setType(NonobligatoryTypes
                                            .valueOf((getXMLText(reader)).toUpperCase()));
                                }
                                break;
                            case FREQUENCY:
                                ((ObligatoryDevice) device).setFrequency(getXMLText(reader));
                                break;
                            case RAMTYPE:
                                ((ObligatoryDevice) device).setRAMType(getXMLText(reader));
                                break;
                            case SOCKET:
                                ((ObligatoryDevice) device).setSocket(getXMLText(reader));
                                break;
                            case CHIPSET:
                                ((ObligatoryDevice) device).setChipset(getXMLText(reader));
                                break;
                            case INTERFACE:
                                ((ObligatoryDevice) device).setVolume(getXMLText(reader));
                                break;
                            case VOLUME:
                                ((ObligatoryDevice) device).setVolume(getXMLText(reader));
                                break;
                            case PARAMETERS:
                                ((NonobligatoryDevice) device).setParameters(getXMLText(reader));
                                break;
                            case ISPERIPHERAL:
                                ((NonobligatoryDevice) device)
                                        .setPeripheral(Boolean.parseBoolean(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        element = reader.getLocalName();
                        deviceParserEnum = DeviceParserEnum.valueOf(element.toUpperCase());

                        if (deviceParserEnum == DeviceParserEnum.NONOBLIGATORY_DEVICE ||
                                deviceParserEnum == DeviceParserEnum.OBLIGATORY_DEVICE) {
                            return device;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.warn("XMLStreamException in DeviceStAXParser class");
        }
        return device;
    }

    private Shipment buildShipment(XMLStreamReader reader) {
        Shipment shipment = new Shipment();
        int type;
        String element;
        DeviceParserEnum deviceParserEnum;
        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        element = reader.getLocalName();
                        deviceParserEnum = DeviceParserEnum.valueOf(element.toUpperCase());
                        switch (deviceParserEnum) {
                            case SHIPMENTDATE:
                                shipment.setDate(DeviceDOMParser.parseDate(getXMLText(reader)));
                                break;
                            case COMPANY:
                                shipment.setCompany(getXMLText(reader));
                                break;
                            case PRICE:
                                shipment.setPrice(Double.parseDouble(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        element = reader.getLocalName();
                        deviceParserEnum = DeviceParserEnum.valueOf(element.toUpperCase());
                        if (deviceParserEnum == DeviceParserEnum.SHIPMENT) {
                            return shipment;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.warn("XMLStreamException in DeviceStAXParser class");
        }
        return shipment;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
