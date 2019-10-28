package br.com.xmacedo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.xmacedo.entity.Lane;
import br.com.xmacedo.model.LaneDto;
import br.com.xmacedo.repository.LaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.asin;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

@Service
public class LaneServiceImpl implements LaneService {

    @Autowired
    private LaneRepository laneRepository;

    @Autowired
    private ItineraryService itineraryService;

    @Override
    public List<LaneDto> findAll() {
        return this.laneRepository.findAll().stream()
            .map(lane -> lane.buildToLaneDTO())
            .collect(Collectors.toList());
    }

    @Override
    public List<LaneDto> findByName(String nome) {
        Optional<List<Lane>> lane = this.laneRepository.findByNomeIgnoreCase(nome);
        if (lane.isPresent()) {
            return lane.get().stream().map(lane1 -> {
                LaneDto laneDto = lane1.buildToLaneDTO();
                laneDto.setListaItinerario(this.itineraryService.findItineraryByIdLinha(laneDto.getIdLinhaPoaTransporte()));
                return laneDto;
            }).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<LaneDto> findLaneDtoById(Long id) {
        Optional<Lane> lane = this.laneRepository.findById(id);
        if (lane.isPresent()) {
            LaneDto laneDto = lane.get().buildToLaneDTO();
            laneDto.setListaItinerario(this.itineraryService.findItineraryByIdLinha(laneDto.getIdLinhaPoaTransporte()));
            return Optional.of(laneDto);
        }
        return Optional.empty();
    }

    private Optional<Lane> findLaneById(Long id) {
        return this.laneRepository.findById(id);
    }

    @Override
    public List<LaneDto> findLanesByRadius(Double latitude, Double longitude, Double raio) throws IOException {

        Double raioTerra = 6371.0;
        Double raioBusca = raio / raioTerra;

        Double latMax = latitude + raioBusca;
        Double latMin = latitude - raioBusca;

        Double arcoLng = asin(sin(raioBusca) / cos(latitude));

        Double lngMin = longitude - arcoLng;
        Double lngMax = longitude + arcoLng;

        List<Lane> lanesList = this.laneRepository.findByLatMinAndLatMaxAndLongMinAndLonMax(latMin, latMax, lngMin, lngMax);

        return lanesList.stream().map(lane1 -> {
            LaneDto laneDto = lane1.buildToLaneDTO();
            laneDto.setListaItinerario(this.itineraryService.findItineraryByIdLinha(laneDto.getIdLinhaPoaTransporte()));
            return laneDto;
        }).collect(Collectors.toList());

    }

    @Override
    public LaneDto createOrUpdateLane(LaneDto laneDto) {
        return this.findLaneDtoById(this.laneRepository.save(laneDto.buildToLane()).getId()).get();
    }

    @Override
    public String deleteLaneById(Long id) {
        Optional<Lane> lane = findLaneById(id);
        if (lane.isPresent()) {
            this.laneRepository.delete(lane.get());
            return "Id " + id + " deletado com sucesso.";
        }
        return "Id " + id + " nao encontrado.";
    }

    @Override
    public Optional<LaneDto> findLaneDtoByIdLane(Long idLinha) {
        Optional<Lane> lane = this.laneRepository.findByIdLinhaPoaTransporte(idLinha);
        if (lane.isPresent()) {
            LaneDto laneDto = lane.get().buildToLaneDTO();
            laneDto.setListaItinerario(this.itineraryService.findItineraryByIdLinha(laneDto.getIdLinhaPoaTransporte()));
            return Optional.of(laneDto);
        }
        return Optional.empty();
    }
}