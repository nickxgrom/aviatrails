package dev.nxgr.aviatrails.Route;

import dev.nxgr.aviatrails.Location.Location;
import jakarta.persistence.*;

@Entity
public class Route {
    @Id
//    @SequenceGenerator(name = "route_sequence", sequenceName = "route_sequence")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "departure")
    private Location departure;
    @ManyToOne
    @JoinColumn(name = "destination")
    private Location destination;
    private Long flightStartUTC;

    private Long flightTimeMinutes;
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

    public Long getFlightStartUTC() {
        return flightStartUTC;
    }

    public void setFlightStartUTC(Long flightStartUTC) {
        this.flightStartUTC = flightStartUTC;
    }

    public Long getFlightTimeMinutes() {
        return flightTimeMinutes;
    }

    public void setFlightTimeMinutes(Long flightTimeMinutes) {
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

    public Route(Long id, Location departure, Location destination, Long flightStartUTC, Long flightTimeMinutes, int distance, double coast, int amount) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.flightStartUTC = flightStartUTC;
        this.flightTimeMinutes = flightTimeMinutes;
        this.distance = distance;
        this.coast = coast;
        this.amount = amount;
    }

    public Route(Location departure, Location destination, Long flightStartUTC, Long flightTimeMinutes, int distance, double coast, int amount) {
        this.departure = departure;
        this.destination = destination;
        this.flightStartUTC = flightStartUTC;
        this.flightTimeMinutes = flightTimeMinutes;
        this.distance = distance;
        this.coast = coast;
        this.amount = amount;
    }

    public Route() {
    }
}
