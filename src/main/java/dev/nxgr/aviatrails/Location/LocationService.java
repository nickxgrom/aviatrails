package dev.nxgr.aviatrails.Location;

import org.springframework.stereotype.Service;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location add(Location location) {
        return locationRepository.save(location);
    }
}
