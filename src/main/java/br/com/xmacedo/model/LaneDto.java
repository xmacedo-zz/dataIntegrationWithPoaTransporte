package br.com.xmacedo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.xmacedo.entity.Lane;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaneDto {

    @JsonProperty("id")
    private Long                     id;
    @JsonProperty("idlinha")
    private Long                     idLinhaPoaTransporte;
    @JsonProperty("nome")
    private String                   nome;
    @JsonProperty("codigo")
    private String                   codigo;
    @JsonIgnore
    private Map<String, LocationDto> mapItinerario = new HashMap<>();

    @JsonAnyGetter
    public Map<String, LocationDto> getItinerario() {
        return this.mapItinerario;
    }

    @JsonAnySetter
    public void setItinerario(String name, LocationDto value) {
        mapItinerario.put(name, value);
    }

    public List<ItineraryDto> listaItinerario;

    public Lane buildToLane() {
        return Lane.builder()
            .id(this.id)
            .idLinhaPoaTransporte(this.idLinhaPoaTransporte)
            .codigo(this.codigo)
            .nome(this.nome)
            .build();
    }

    public Lane buildToLaneFromApi() {
        return Lane.builder()
            .idLinhaPoaTransporte(this.id)
            .codigo(this.codigo)
            .nome(this.nome)
            .build();
    }
}
