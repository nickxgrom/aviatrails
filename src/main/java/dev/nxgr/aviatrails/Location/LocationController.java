package dev.nxgr.aviatrails.Location;

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
    public List<Location> searchLocationList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam Optional<String> searchQuery
    ) {
        return locationService.search(page, pageSize, searchQuery.orElse(""));
    }
}
