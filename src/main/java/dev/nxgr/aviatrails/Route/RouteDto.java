package dev.nxgr.aviatrails.Route;

public class RouteDto {
    private Long departureId;
    private Long destinationId;
    private Long flightStartUtc;

    private double coast;
    private int amount;

    public Long getDepartureId() {
        return departureId;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public Long getFlightStartUtc() {
        return flightStartUtc;
    }

    public double getCoast() {
        return coast;
    }

    public void setCoast(double coast) {
        this.coast = coast;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
