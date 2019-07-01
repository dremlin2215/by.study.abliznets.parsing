package beans;

import java.util.Objects;

public class NonobligatoryDevice extends Device {
    private boolean isObligatory;
    private NonobligatoryTypes type;
    private String parameters;
    private boolean isPeripheral;

    public NonobligatoryDevice(String id, String name, Shipment shipment, boolean hasCooler, String ports, double energyConsumption, boolean isObligatory, NonobligatoryTypes type, String parameters, boolean isPeripheral) {
        super(id, name, shipment, hasCooler, ports, energyConsumption);
        this.isObligatory = isObligatory;
        this.type = type;
        this.parameters = parameters;
        this.isPeripheral = isPeripheral;
    }

    public NonobligatoryDevice() {

    }

    public boolean isObligatory() {
        return isObligatory;
    }

    public void setObligatory(boolean obligatory) {
        isObligatory = obligatory;
    }

    public NonobligatoryTypes getType() {
        return type;
    }

    public void setType(NonobligatoryTypes type) {
        this.type = type;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public boolean isPeripheral() {
        return isPeripheral;
    }

    public void setPeripheral(boolean peripheral) {
        isPeripheral = peripheral;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NonobligatoryDevice)) {
            return false;
        }
        if (!super.equals(o)) return false;
        NonobligatoryDevice that = (NonobligatoryDevice) o;
        return isObligatory == that.isObligatory &&
                isPeripheral == that.isPeripheral &&
                type == that.type &&
                Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), isObligatory, type, parameters, isPeripheral);
    }

    @Override
    public String toString() {
        return "NonobligatoryDevice{" +
                "isObligatory=" + isObligatory +
                ", type=" + type +
                ", parameters='" + parameters + '\'' +
                ", isPeripheral=" + isPeripheral +
                "} " + super.toString();
    }
}