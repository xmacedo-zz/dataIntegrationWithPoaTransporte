package br.com.xmacedo.service;

import java.util.Map;

import br.com.xmacedo.entity.Location;
import br.com.xmacedo.model.LaneDto;
import br.com.xmacedo.model.LocationDto;
import br.com.xmacedo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ItineraryService itineraryService;

    @Override
    public void saveLocationAndItinerary(LaneDto laneDto){
        Map<String, LocationDto> itinerarioLinha = laneDto.getItinerario();
        itinerarioLinha.entrySet().forEach(locationFromItinerary -> {
            Location location = locationFromItinerary.getValue().mapBuilder();
            this.saveLocation(location);
            itineraryService.saveItinerary(laneDto, location, locationFromItinerary.getKey());
        });
    }
    private void saveLocation (Location location){
        this.locationRepository.save(location);
    }
}
