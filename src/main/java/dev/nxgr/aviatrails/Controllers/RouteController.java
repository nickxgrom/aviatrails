package dev.nxgr.aviatrails.Controllers;

import dev.nxgr.aviatrails.entities.Location;
import dev.nxgr.aviatrails.entities.Route;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    @PostMapping("/test-post")
    public String sayHello() {
        return "hello";
    }
}
