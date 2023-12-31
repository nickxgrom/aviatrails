package dev.nxgr.aviatrails.Location;

import jakarta.persistence.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String airportCode;
    private String airportName;
    private String cityName;
    private double latitude;
    private double longitude;

    public Location(String country, String airportCode, String airportName, String cityName, double longitude, double latitude) {
        this.airportCode = airportCode;
        this.cityName = cityName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityCode) {
        this.cityName = cityCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double altitude) {
        this.longitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
