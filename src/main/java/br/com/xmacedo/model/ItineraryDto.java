package br.com.xmacedo.model;

import br.com.xmacedo.entity.Itinerary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDto {
    private Long idLinha;
    private Long idLocalizacao;
    private int  trajeto;

    public Itinerary buildToItinerary() {
        return Itinerary.builder()
            .idLinha(this.idLinha)
            .idLocalizacao(this.idLocalizacao)
            .trajeto(this.trajeto)
            .build();
    }
}
