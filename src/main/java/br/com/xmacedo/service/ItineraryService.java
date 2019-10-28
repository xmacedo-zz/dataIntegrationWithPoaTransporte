package br.com.xmacedo.service;

import java.util.List;

import br.com.xmacedo.entity.Location;
import br.com.xmacedo.model.ItineraryDto;
import br.com.xmacedo.model.LaneDto;

public interface ItineraryService {
    List<ItineraryDto> findAll();

    void saveItinerary(LaneDto laneDto, Location location, String route);

    List<ItineraryDto> findItineraryByIdLinha(Long idLinha);

    List<ItineraryDto> createOrUpdateItinerary(ItineraryDto form);

    String deleteItinerary(Long idLinha, Long idLocalizacao);
}
