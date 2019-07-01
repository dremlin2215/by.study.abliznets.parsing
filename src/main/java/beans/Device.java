package beans;

import java.util.Objects;

public class Device {
    private String id;
    private String name;
    private Shipment shipment;
    private boolean hasCooler;
    private String ports;
    private double energyConsumption;

    public Device() {
        this.shipment = new Shipment();
    }

    public Device(String id, String name, Shipment shipment, boolean hasCooler, String ports, double energyConsumption) {
        this.id = id;
        this.name = name;
        this.shipment = shipment;
        this.hasCooler = hasCooler;
        this.ports = ports;
        this.energyConsumption = energyConsumption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public boolean isHasCooler() {
        return hasCooler;
    }

    public void setHasCooler(boolean hasCooler) {
        this.hasCooler = hasCooler;
    }

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return hasCooler == device.hasCooler &&
                Double.compare(device.energyConsumption, energyConsumption) == 0 &&
                Objects.equals(id, device.id) &&
                Objects.equals(name, device.name) &&
                Objects.equals(shipment, device.shipment) &&
                Objects.equals(ports, device.ports);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, shipment, hasCooler, ports, energyConsumption);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shipment=" + shipment +
                ", hasCooler=" + hasCooler +
                ", ports='" + ports + '\'' +
                ", energyConsumption=" + energyConsumption +
                '}';
    }
}
