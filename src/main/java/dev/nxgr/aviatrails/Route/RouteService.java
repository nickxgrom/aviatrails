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

        int distance = calcDistance(departure, destination);

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

    public void deleteById(Long id) {
        routeRepository.deleteById(id);
    }

    public Route update(Long id, RouteDto route) {
        Route dbRoute = routeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not-found"));

        if (route.getDepartureId() != null) {
            Long departureId = route.getDepartureId();
            Location departure = locationService.getById(departureId);
            dbRoute.setDeparture(departure);
        }

        if (route.getDestinationId() != null) {
            Long destinationId = route.getDestinationId();
            Location destination = locationService.getById(destinationId);
            dbRoute.setDestination(destination);
        }

        if (route.getFlightStartUtc() != null) {
            dbRoute.setFlightStartUtc(route.getFlightStartUtc());
        }

        if (route.getCoast() != 0) {
            dbRoute.setCoast(route.getCoast());
        }

        if (route.getAmount() != 0) {
            dbRoute.setAmount(route.getAmount());
        }

        int distance = calcDistance(dbRoute.getDeparture(), dbRoute.getDestination());
        int flightMinutes = (distance / 817) * 60;

        dbRoute.setDistance(distance);
        dbRoute.setFlightTimeMinutes(flightMinutes);

        return routeRepository.save(dbRoute);

    }

    private int calcDistance(Location departure, Location destination) {
        return (int) (Math.acos(Math.sin(departure.getLatitude()) * Math.sin(destination.getLatitude())
                + Math.cos(departure.getLatitude()) * Math.cos(destination.getLatitude())
                * Math.cos(destination.getLongitude() - departure.getLongitude())) * 6371);
    }
}
