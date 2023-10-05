package dev.nxgr.aviatrails.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Location {
    @Id
    private String airportCode;
    private String cityCode;
    private float altitude;
    private float latitude;
}
