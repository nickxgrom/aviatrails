package dev.nxgr.aviatrails.Route;

import dev.nxgr.aviatrails.Location.Location;
import dev.nxgr.aviatrails.Location.LocationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    public final LocationService locationService;

    public RouteService(RouteRepository routeRepository, LocationService locationService) {
        this.routeRepository = routeRepository;
        this.locationService = locationService;
    }

    public Long addRoute(RouteDto dto) {
        Location departure = locationService.getById(dto.getDepartureId());
        Location destination = locationService.getById(dto.getDestinationId());

        int distance = (int) (Math.acos(Math.sin(departure.getLatitude()) * Math.sin(destination.getLatitude())
                        + Math.cos(departure.getLatitude()) * Math.cos(destination.getLatitude())
                        * Math.cos(destination.getLongitude() - departure.getLongitude())) * 6371);

        int flightTimeMinutes = (distance / 817) * 60;

        Route route = new Route(departure, destination, dto.getFlightStartUtc(), flightTimeMinutes, distance, dto.getCoast(), dto.getAmount());

        return routeRepository.save(route).getId();
    }

    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    public Route getById(Long id) {
        return routeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not-found"));
    }

    public List<Route> search(int page, int pageSize, Long departureId, Long destinationId) {
        Pageable paging = PageRequest.of(page, pageSize);
        return routeRepository.search(paging, departureId, destinationId);
    }
}
