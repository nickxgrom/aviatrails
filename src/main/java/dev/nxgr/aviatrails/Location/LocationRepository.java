package dev.nxgr.aviatrails.Location;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("select l from Location l where l.country like %:searchQuery% or l.cityName like %:searchQuery% or l.airportName like %:searchQuery%")
    List<Location> search(Pageable pageable, @Param("searchQuery") String searchQuery);
}
