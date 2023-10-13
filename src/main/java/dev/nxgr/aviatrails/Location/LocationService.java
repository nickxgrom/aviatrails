package dev.nxgr.aviatrails.Location;

import jakarta.websocket.Session;
import org.hibernate.boot.SessionFactoryBuilder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location add(Location location) {
        return locationRepository.save(location);
    }

    public void update(Location location) {

    }

    public void delete(Long id) {

    }

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Location getById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
    }

    public List<Location> search(int page, int pageSize, String searchQuery) {
        System.out.println(searchQuery);
        return locationRepository.search(searchQuery);
    }
}
