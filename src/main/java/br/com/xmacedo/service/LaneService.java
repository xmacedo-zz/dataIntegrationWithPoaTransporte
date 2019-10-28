package br.com.xmacedo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import br.com.xmacedo.model.LaneDto;

public interface LaneService {

    List<LaneDto> findAll();

    List<LaneDto> findByName(String nome);

    Optional<LaneDto> findLaneDtoById(Long id);

    Optional<LaneDto> findLaneDtoByIdLane(Long idLinha);

    String deleteLaneById(Long id);

    LaneDto createOrUpdateLane(LaneDto laneDto);

    List<LaneDto> findLanesByRadius(Double latitude,Double longitude, Double raio) throws IOException;
}
