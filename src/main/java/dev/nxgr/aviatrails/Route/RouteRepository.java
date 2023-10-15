package dev.nxgr.aviatrails.Route;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("select r from Route r where r.departure.id = :departure and r.destination.id = :destination")
    List<Route> search(
        Pageable pageable,
        @Param("departure") Long departure,
        @Param("destination") Long destination
    );

    List<Route> findAllByDeparture_IdOrDestination_Id(Long departure_id, Long destination_id);
}
