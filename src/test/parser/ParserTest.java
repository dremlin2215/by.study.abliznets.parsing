package parser;

import beans.*;
import factory.ParserFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ParserTest {

    private static final Path CORRECT_FILE_PATH =
            Paths.get("C:\\Users\\User\\IdeaProjects\\by.study.abliznets.parsing\\src\\main\\resources\\correctDevicesForTesting.xml");
    private List<Device> expectedDeviceList;
    private List<Device> resultDeviceList;

    @BeforeTest
    public void prepare() throws ParseException {

        expectedDeviceList = new ArrayList<>();

        Device device1 = new ObligatoryDevice();
        device1.setId("A432gt");
        device1.setName("WD Caviar Blue 1Tb");
        device1.getShipment().setPrice(115);
        device1.getShipment().setCompany("Western Digital");
        device1.getShipment().setDate(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2018-12-11"));
        device1.setHasCooler(false);
        device1.setPorts("SATA");
        device1.setEnergyConsumption(0);

        ((ObligatoryDevice) device1).setObligatory(true);
        ((ObligatoryDevice) device1).setType(ObligatoryTypes.HDD);
        ((ObligatoryDevice) device1).setVolume("1Tb");

        Device device2 = new NonobligatoryDevice();
        device2.setId("C452");
        device2.setName("Logitech G413 Carbon");
        device2.getShipment().setPrice(160);
        device2.getShipment().setCompany("Logitech");
        device2.getShipment().setDate(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2019-02-01"));
        device2.setHasCooler(false);
        device2.setEnergyConsumption(0);

        ((NonobligatoryDevice) device2).setObligatory(false);
        ((NonobligatoryDevice) device2).setType(NonobligatoryTypes.KEYBOARD);
        ((NonobligatoryDevice) device2).setParameters("Mechanical");
        ((NonobligatoryDevice) device2).setPeripheral(true);

        expectedDeviceList.add(device1);
        expectedDeviceList.add(device2);
    }

    @AfterTest
    public void destroy() {
        expectedDeviceList = null;
        resultDeviceList = null;
    }

    @Test
    public void DOMParserTest() throws IOException, ParserConfigurationException {
        AbstractDeviceParser parser = ParserFactory.INSTANCE.createParser("DOM");
        InputStream stream = Files.newInputStream(CORRECT_FILE_PATH);
        parser.buildDeviceList(stream);

        resultDeviceList = parser.getDeviceList();
        Assert.assertTrue(resultDeviceList.equals(expectedDeviceList));

    }
    @Test
    public void SAXParserTest() throws IOException, ParserConfigurationException {
        AbstractDeviceParser parser = ParserFactory.INSTANCE.createParser("SAX");
        InputStream stream = Files.newInputStream(CORRECT_FILE_PATH);
        parser.buildDeviceList(stream);

        resultDeviceList = parser.getDeviceList();
        Assert.assertTrue(resultDeviceList.equals(expectedDeviceList));

    }
    @Test
    public void StAXParserTest() throws IOException, ParserConfigurationException {
        AbstractDeviceParser parser = ParserFactory.INSTANCE.createParser("StAX");
        InputStream stream = Files.newInputStream(CORRECT_FILE_PATH);
        parser.buildDeviceList(stream);

        resultDeviceList = parser.getDeviceList();
        Assert.assertTrue(resultDeviceList.equals(expectedDeviceList));

    }
}
