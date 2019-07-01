package beans;

public enum ObligatoryTypes {
    POWERSUPPLY("powersupply"), RAM("RAM"), MOTHERBOARD("motherboard"), CPU("CPU"), HDD("HDD");
    String value;

    ObligatoryTypes(String type){

    }

    public String getValue() {
        return value;
    }
}
