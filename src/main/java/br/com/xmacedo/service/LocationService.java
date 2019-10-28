package br.com.xmacedo.service;

import br.com.xmacedo.model.LaneDto;

public interface LocationService {
    void saveLocationAndItinerary(LaneDto laneDto);
}
