package dev.nxgr.aviatrails.Route;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    public final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/route")
    public void createRoute() {
        routeService.addRoute();
    }
}
