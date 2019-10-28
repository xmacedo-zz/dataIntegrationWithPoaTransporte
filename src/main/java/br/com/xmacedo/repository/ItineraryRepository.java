package br.com.xmacedo.repository;

import java.util.List;
import java.util.Optional;

import br.com.xmacedo.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    List<Itinerary> findByIdLinha(@Param("idLinha") Long idLinha);

    Optional<Itinerary> findByIdLinhaAndIdLocalizacao(@Param("idLinha") Long idLinha,
        @Param("idLocalizacao") Long idLocalizacao);
}
