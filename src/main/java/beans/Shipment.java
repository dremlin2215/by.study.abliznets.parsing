package beans;

import java.util.Date;
import java.util.Objects;

public class Shipment {
    private String company;
    private Date date;
    private double price;

    public Shipment() {
    }

    public Shipment(String company, Date date, double price) {
        this.company = company;
        this.date = date;
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Shipment shipment = (Shipment) o;
        return Double.compare(shipment.price, price) == 0 &&
                Objects.equals(company, shipment.company) &&
                Objects.equals(date, shipment.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(company, date, price);
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "company='" + company + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
