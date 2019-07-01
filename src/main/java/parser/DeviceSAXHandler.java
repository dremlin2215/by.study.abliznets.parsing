package parser;

import beans.*;
import exception.UnimplementedOperationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DeviceSAXHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(DeviceSAXHandler.class);
    private List<Device> deviceList;
    private Device currentDevice = null;
    private DeviceParserEnum currentDeviceEnum = null;
    private EnumSet<DeviceParserEnum> elementWithText;

    public DeviceSAXHandler() {
        deviceList = new ArrayList<>();
        elementWithText = EnumSet.range(DeviceParserEnum.ID, DeviceParserEnum.ISPERIPHERAL);
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void startDocument() {
        LOGGER.info("Starting SAX parsing...");
    }


    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        DeviceParserEnum localDeviceEnum = DeviceParserEnum.valueOf(localName.toUpperCase());
        switch (localDeviceEnum) {
            case OBLIGATORY_DEVICE:
                currentDevice = new ObligatoryDevice();
                currentDevice.setId(attrs.getValue(DeviceParserEnum.ID.getValue()));
                break;
            case NONOBLIGATORY_DEVICE:
                currentDevice = new NonobligatoryDevice();
                currentDevice.setId(attrs.getValue(DeviceParserEnum.ID.getValue()));
                break;
            default:
                if (elementWithText.contains(DeviceParserEnum.valueOf(localName.toUpperCase()))) {
                    currentDeviceEnum = localDeviceEnum;
                }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (DeviceParserEnum.OBLIGATORY_DEVICE.getValue().equals(localName) ||
                DeviceParserEnum.NONOBLIGATORY_DEVICE.getValue().equals(localName)) {
            deviceList.add(currentDevice);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String string = new String(ch, start, length).trim();
        if (currentDeviceEnum != null) {
            switch (currentDeviceEnum) {
                case NAME:
                    currentDevice.setName(string);
                    break;
                case COMPANY:
                    currentDevice.getShipment().setCompany(string);
                    break;
                case SHIPMENTDATE:
                    currentDevice.getShipment().setDate(DeviceDOMParser.parseDate(string));
                    break;
                case PRICE:
                    currentDevice.getShipment().setPrice(Double.parseDouble(string));
                    break;
                case ISOBLIGATORY:
                    if (currentDevice instanceof ObligatoryDevice) {
                        ((ObligatoryDevice) currentDevice).setObligatory(true);
                    } else {
                        ((NonobligatoryDevice) currentDevice).setObligatory(false);
                    }
                    break;
                case HASCOOLER:
                    currentDevice.setHasCooler(Boolean.parseBoolean(string));
                    break;
                case PORTS:
                    currentDevice.setPorts(string);
                    break;
                case ENERGYCONSUMPTION:
                    currentDevice.setEnergyConsumption(Double.parseDouble(string));
                    break;
                case TYPE:
                    if (currentDevice instanceof ObligatoryDevice) {
                        ((ObligatoryDevice) currentDevice)
                                .setType(ObligatoryTypes.valueOf(string.toUpperCase()));
                    } else if (currentDevice instanceof NonobligatoryDevice) {
                        ((NonobligatoryDevice) currentDevice)
                                .setType(NonobligatoryTypes.valueOf(string.toUpperCase()));
                    } else {
                        throw new UnimplementedOperationException();
                    }
                    break;
                case FREQUENCY:
                    ((ObligatoryDevice) currentDevice).setFrequency(string);
                    break;
                case RAMTYPE:
                    ((ObligatoryDevice) currentDevice).setRAMType(string);
                    break;
                case SOCKET:
                    ((ObligatoryDevice) currentDevice).setFrequency(string);
                    break;
                case CHIPSET:
                    ((ObligatoryDevice) currentDevice).setChipset(string);
                    break;
                case INTERFACE:
                    ((ObligatoryDevice) currentDevice).setInterface_(string);
                    break;
                case VOLUME:
                    ((ObligatoryDevice) currentDevice).setVolume(string);
                    break;
                case PARAMETERS:
                    ((NonobligatoryDevice) currentDevice).setParameters(string);
                    break;
                case ISPERIPHERAL:
                    ((NonobligatoryDevice) currentDevice).setPeripheral(Boolean.parseBoolean(string));
                    break;
                default:
                    throw new UnimplementedOperationException("Unknown parameter");
            }
        }
        currentDeviceEnum = null;
    }

    public void endDocument() {
        LOGGER.info("Parsing SAX finished");
    }
}
