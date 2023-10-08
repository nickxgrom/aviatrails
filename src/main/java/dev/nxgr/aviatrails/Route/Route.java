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
    private String departureTime;

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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Route(Location departure, Location destination, String departureTime) {
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    public Route() {

    }
}
