package dev.nxgr.aviatrails.Route;

public class RouteDto {
    private Long departureId;
    private Long destinationId;
    private Long flightStartUTC;

    private Long flightTimeMinutes;
    private int distance;

    private double coast;
    private int amount;

    public Long getDepartureId() {
        return departureId;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public Long getFlightStartUTC() {
        return flightStartUTC;
    }

    public Long getFlightTimeMinutes() {
        return flightTimeMinutes;
    }

    public int getDistance() {
        return distance;
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
