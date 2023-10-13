package dev.nxgr.aviatrails.Location;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/search")
    public List<Location> searchLocationList(@RequestParam Optional<String> searchQuery) {
        return locationService.search(10, 3, searchQuery.get());
    }
}
