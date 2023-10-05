package dev.nxgr.aviatrails.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.type.descriptor.jdbc.TimeWithTimeZoneJdbcType;

@Entity
public class Route {
    @Id
    private Long id;
    @ManyToOne
    private Location departure;
    @ManyToOne
    private Location destination;
    private String departureTime;
}
