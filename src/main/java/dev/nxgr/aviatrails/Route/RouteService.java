package dev.nxgr.aviatrails.Route;

import dev.nxgr.aviatrails.Location.Location;
import dev.nxgr.aviatrails.Location.LocationService;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    public final LocationService locationService;

    public RouteService(RouteRepository routeRepository, LocationService locationService) {
        this.routeRepository = routeRepository;
        this.locationService = locationService;
    }

    public void addRoute() {
        Location departureCity = locationService.add(new Location("ALA", "ALA", 43.23, 43.23));
        Location destinationCity = locationService.add(new Location("AST", "AST", 45.23, 41.23));
        Route route = new Route(departureCity, destinationCity, "18:20");
        routeRepository.save(route);
    }
}
