package parser;

public enum DeviceParserEnum {
    DEVICES("device"),
    OBLIGATORY_DEVICE("obligatory_device"),
    NONOBLIGATORY_DEVICE("nonobligatory_device"),
    SHIPMENT("shipment"),
    ID("id"),
    NAME("name"),
    COMPANY("company"),
    SHIPMENTDATE("shipmentDate"),
    PRICE("price"),
    ISOBLIGATORY("isObligatory"),
    HASCOOLER("hasCooler"),
    PORTS("ports"),
    ENERGYCONSUMPTION("energyConsumption"),
    TYPE("type"),
    //Obligatory here
    FREQUENCY("frequency"),
    RAMTYPE("RAMType"),
    SOCKET("socket"),
    CHIPSET("chipset"),
    INTERFACE("interface"),
    VOLUME("volume"),
    //Nonobligatory here
    PARAMETERS("parameters"),
    ISPERIPHERAL("isPeripheral");


    private String value;

    DeviceParserEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
