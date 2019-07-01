package beans;

import java.util.Objects;

public class ObligatoryDevice extends Device {
    private boolean isObligatory;
    private ObligatoryTypes type;
    private String frequency;
    private String RAMType;
    private String socket;
    private String chipset;
    private String interface_;
    private String volume;

    public ObligatoryDevice(String id, String name, Shipment shipment, boolean hasCooler,
                            String ports, double energyConsumption, boolean isObligatory,
                            ObligatoryTypes type, String frequency, String RAMType, String socket,
                            String chipset, String interface_, String volume) {
        super(id, name, shipment, hasCooler, ports, energyConsumption);
        this.isObligatory = isObligatory;
        this.type = type;
        this.frequency = frequency;
        this.RAMType = RAMType;
        this.socket = socket;
        this.chipset = chipset;
        this.interface_ = interface_;
        this.volume = volume;
    }

    public ObligatoryDevice() {

    }

    public boolean isObligatory() {
        return isObligatory;
    }

    public void setObligatory(boolean obligatory) {
        isObligatory = obligatory;
    }

    public ObligatoryTypes getType() {
        return type;
    }

    public void setType(ObligatoryTypes type) {
        this.type = type;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getRAMType() {
        return RAMType;
    }

    public void setRAMType(String RAMType) {
        this.RAMType = RAMType;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getInterface_() {
        return interface_;
    }

    public void setInterface_(String interface_) {
        this.interface_ = interface_;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObligatoryDevice)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ObligatoryDevice that = (ObligatoryDevice) o;
        return isObligatory == that.isObligatory &&
                type == that.type &&
                Objects.equals(frequency, that.frequency) &&
                Objects.equals(RAMType, that.RAMType) &&
                Objects.equals(socket, that.socket) &&
                Objects.equals(chipset, that.chipset) &&
                Objects.equals(interface_, that.interface_) &&
                Objects.equals(volume, that.volume);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), isObligatory, type, frequency, RAMType, socket, chipset, interface_, volume);
    }

    @Override
    public String toString() {
        return "ObligatoryDevice{" +
                "isObligatory=" + isObligatory +
                ", type=" + type +
                ", frequency='" + frequency + '\'' +
                ", RAMType='" + RAMType + '\'' +
                ", socket='" + socket + '\'' +
                ", chipset='" + chipset + '\'' +
                ", interface_='" + interface_ + '\'' +
                ", volume='" + volume + '\'' +
                "} " + super.toString();
    }
}
