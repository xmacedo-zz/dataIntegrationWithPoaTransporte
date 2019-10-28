package br.com.xmacedo.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import br.com.xmacedo.configuration.ItineraryId;
import br.com.xmacedo.model.ItineraryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ItineraryId.class)
@Table(name = "itinerario")
public class Itinerary implements Serializable {
    @Id
    @Column(name = "idLinha", nullable = false)
    private Long idLinha;

    @Id
    @Column(name = "idLocalizacao", nullable = false)
    private Long idLocalizacao;

    @Column(name = "trajeto")
    private int trajeto;

    public ItineraryDto buildToItineraryDto() {
        return ItineraryDto.builder()
            .idLinha(this.idLinha)
            .idLocalizacao(this.idLocalizacao)
            .trajeto(this.trajeto)
            .build();
    }
}
