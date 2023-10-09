package dev.nxgr.aviatrails.Location;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public void add(@RequestBody Location location) {
        locationService.add(location);
    }

    @GetMapping
    public List<Location> getAll() {
        return locationService.getAll();
    }

    @GetMapping("{id}")
    public Location getById(@PathVariable Long id) {
        return locationService.getById(id);
    }
}
