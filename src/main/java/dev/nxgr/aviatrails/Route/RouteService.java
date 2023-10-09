package dev.nxgr.aviatrails.Route;

import dev.nxgr.aviatrails.Location.Location;
import dev.nxgr.aviatrails.Location.LocationService;
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

    public void addRoute(RouteDto dto) {
        Location departure = locationService.getById(dto.getDepartureId());
        Location destination = locationService.getById(dto.getDepartureId());

        Route route = new Route(departure, destination, dto.getFlightStartUTC(), dto.getFlightTimeMinutes(), dto.getDistance(), dto.getCoast(), dto.getAmount());

        routeRepository.save(route);
    }

    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    public Route getById(Long id) {
        return routeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not-found"));
    }
}
