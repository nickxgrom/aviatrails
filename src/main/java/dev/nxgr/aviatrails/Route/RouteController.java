package dev.nxgr.aviatrails.Route;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    public final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public Long createRoute(@RequestBody RouteDto route) {
        return routeService.addRoute(route);
    }

    @GetMapping
    public List<Route> getAll() {
        return routeService.getAll();
    }

    @GetMapping("{id}")
    public Route getById(@PathVariable Long id) {
        return routeService.getById(id);
    }

    @GetMapping("/search")
    public List<Route> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam Long departureId,
            @RequestParam Long destinationId
    ) {
        return routeService.search(page, pageSize, departureId, destinationId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        routeService.deleteById(id);
    }

    @PutMapping("{id}")
    public Route update(@PathVariable Long id, @RequestBody RouteDto route) {
        return routeService.update(id, route);
    }
}
