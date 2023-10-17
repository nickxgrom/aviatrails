package dev.nxgr.aviatrails.Route;

import dev.nxgr.aviatrails.Location.Location;
import jakarta.persistence.*;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "departure")
    private Location departure;
    @ManyToOne
    @JoinColumn(name = "destination")
    private Location destination;
    private Long flightStartUtc;

    private int flightTimeMinutes;
    private int distance;
    private double coast;
    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public Long getFlightStartUtc() {
        return flightStartUtc;
    }

    public void setFlightStartUtc(Long flightStartUTC) {
        this.flightStartUtc = flightStartUTC;
    }

    public int getFlightTimeMinutes() {
        return flightTimeMinutes;
    }

    public void setFlightTimeMinutes(int flightTimeMinutes) {
        this.flightTimeMinutes = flightTimeMinutes;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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

    public Route(Long id, Location departure, Location destination, Long flightStartUtc, int flightTimeMinutes, int distance, double coast, int amount) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.flightStartUtc = flightStartUtc;
        this.flightTimeMinutes = flightTimeMinutes;
        this.distance = distance;
        this.coast = coast;
        this.amount = amount;
    }

    public Route(Location departure, Location destination, Long flightStartUtc, int flightTimeMinutes, int distance, double coast, int amount) {
        this.departure = departure;
        this.destination = destination;
        this.flightStartUtc = flightStartUtc;
        this.flightTimeMinutes = flightTimeMinutes;
        this.distance = distance;
        this.coast = coast;
        this.amount = amount;
    }

    public Route() {
    }
}
