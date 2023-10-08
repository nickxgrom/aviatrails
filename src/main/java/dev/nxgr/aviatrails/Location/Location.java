package dev.nxgr.aviatrails.Location;

import dev.nxgr.aviatrails.Route.Route;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String airportCode;
    private String cityCode;
    private double altitude;
    private double latitude;
    @OneToMany(mappedBy = "departure", cascade = CascadeType.ALL)
    private List<Route> routes;

    public Location(String airportCode, String cityCode, double altitude, double latitude) {
        this.airportCode = airportCode;
        this.cityCode = cityCode;
        this.altitude = altitude;
        this.latitude = latitude;
    }

    public Location() {

    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
