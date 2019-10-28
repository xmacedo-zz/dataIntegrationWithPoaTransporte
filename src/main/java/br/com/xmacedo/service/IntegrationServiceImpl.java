package br.com.xmacedo.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import br.com.xmacedo.model.LaneDto;
import br.com.xmacedo.repository.LaneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.xmacedo.configuration.Constants.BASE_URL;
import static br.com.xmacedo.configuration.Constants.END_LIST_ITINERARY_BY_LANE;
import static br.com.xmacedo.configuration.Constants.END_LIST_LANES;

@Service
public class IntegrationServiceImpl implements IntegrationService{

    @Autowired
    private LaneRepository laneRepository;

    @Autowired
    private LocationService locationService;

    @Override
    public void loadFromAPI() throws IOException {
        List<LaneDto> lanesAPI = findAllLanesAPI();
        for (LaneDto laneDto : lanesAPI) {
            this.laneRepository.save(laneDto.buildToLaneFromApi());
            this.locationService.saveLocationAndItinerary(this.findLaneByIdAPI(laneDto.getId()));
        }
    }

    private List<LaneDto> findAllLanesAPI() throws IOException {
        URL url = new URL(BASE_URL + END_LIST_LANES);
        InputStream json = url.openStream();
        Gson gson = new Gson();
        Type typeListLanes = new TypeToken<List<LaneDto>>() {}.getType();
        return gson.fromJson(new InputStreamReader(json, StandardCharsets.UTF_8), typeListLanes);
    }

    private LaneDto findLaneByIdAPI(Long id) throws IOException {
        URL url = new URL(BASE_URL + END_LIST_ITINERARY_BY_LANE + id);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(url, LaneDto.class);
    }
}
