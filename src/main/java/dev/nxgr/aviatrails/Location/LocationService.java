package dev.nxgr.aviatrails.Location;

import dev.nxgr.aviatrails.NotNullCondition;
import dev.nxgr.aviatrails.Route.Route;
import dev.nxgr.aviatrails.Route.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final RouteRepository routeRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public LocationService(LocationRepository locationRepository, RouteRepository routeRepository, ModelMapper modelMapper) {
        this.locationRepository = locationRepository;
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setPropertyCondition(new NotNullCondition());
    }

    public Location add(Location location) {
        return locationRepository.save(location);
    }

    public Location update(Long id, Location location) {
        location.setId(id);
        Location dbLocation = locationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not-found"));

        if (location.getLongitude() == 0.0) {
            location.setLongitude(dbLocation.getLongitude());
        }

        if (location.getLatitude() == 0.0) {
            location.setLatitude(dbLocation.getLatitude());
        }

        modelMapper.map(location, dbLocation);

        return locationRepository.save(dbLocation);
    }

    public void delete(Long id) {
        List<Route> routes = routeRepository.findAllByDeparture_IdOrDestination_Id(id, id);
        for (Route route : routes) {
            routeRepository.deleteById(route.getId());
        }
        locationRepository.deleteById(id);
    }

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Location getById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
    }

    public List<Location> search(int page, int pageSize, String searchQuery) {
//        TODO: add limit of 50
        Pageable paging = PageRequest.of(page, pageSize);

        return locationRepository.search(paging, searchQuery);
    }
}
