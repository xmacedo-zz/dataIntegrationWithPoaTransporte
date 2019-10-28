package br.com.xmacedo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.xmacedo.entity.Itinerary;
import br.com.xmacedo.entity.Location;
import br.com.xmacedo.model.ItineraryDto;
import br.com.xmacedo.model.LaneDto;
import br.com.xmacedo.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItineraryServiceImpl implements ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Override
    public List<ItineraryDto> findAll() {
        List<Itinerary> itineraryList = this.itineraryRepository.findAll();
        return itineraryList.stream().map(Itinerary::buildToItineraryDto).collect(Collectors.toList());
    }

    @Override
    public void saveItinerary(LaneDto laneDto, Location location, String route) {
        itineraryRepository.save(
            Itinerary.builder()
                .idLinha(laneDto.getIdLinhaPoaTransporte())
                .idLocalizacao(location.getId())
                .trajeto(Integer.parseInt(route))
                .build());
    }

    @Override
    public List<ItineraryDto> findItineraryByIdLinha(Long idLinha) {
        List<Itinerary> itineraryList = this.itineraryRepository.findByIdLinha(idLinha);
        return itineraryList.stream().map(Itinerary::buildToItineraryDto).collect(Collectors.toList());
    }

    @Override
    public List<ItineraryDto> createOrUpdateItinerary(ItineraryDto form) {
        return this.findItineraryByIdLinha(this.itineraryRepository.save(form.buildToItinerary()).getIdLinha());
    }

    @Override
    public String deleteItinerary(Long idLinha, Long idLocalizacao) {
        Optional<Itinerary> itinerary = findItneraryByIdLinhaAndIdLocalizacao(idLinha, idLocalizacao);
        if (itinerary.isPresent()) {
            this.itineraryRepository.delete(itinerary.get());
            return "IdLinha " + idLinha + " e idLocalizacao " + idLocalizacao + "  deletado com sucesso.";
        }
        return "IdLinha " + idLinha + " e idLocalizacao " + idLocalizacao + " nao encontrado.";
    }

    private Optional<Itinerary> findItneraryByIdLinhaAndIdLocalizacao(Long idLinha, Long idLocalizacao) {
        return this.itineraryRepository.findByIdLinhaAndIdLocalizacao(idLinha, idLocalizacao);
    }
}
