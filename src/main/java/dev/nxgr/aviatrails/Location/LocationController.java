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
    public Long add(@RequestBody Location location) {
        return locationService.add(location).getId();
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

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        locationService.delete(id);
    }

    @PutMapping("{id}")
    public Location update(@PathVariable Long id, @RequestBody Location location) {
        return locationService.update(id, location);
    }
}
