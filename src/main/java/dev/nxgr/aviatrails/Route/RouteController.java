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
    public void createRoute(@RequestBody RouteDto route) {
        routeService.addRoute(route);
    }

    @GetMapping
    public List<Route> getAll() {
        return routeService.getAll();
    }

    @GetMapping("{id}")
    public Route getById(@PathVariable Long id) {
        return routeService.getById(id);
    }
}
