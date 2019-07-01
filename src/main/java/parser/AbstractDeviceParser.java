package parser;

import beans.Device;

import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDeviceParser {

    protected List<Device> deviceList;

    public AbstractDeviceParser() {
        this.deviceList = new ArrayList<>();
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    abstract public void buildDeviceList(InputStream stream) throws ParserConfigurationException;
}
