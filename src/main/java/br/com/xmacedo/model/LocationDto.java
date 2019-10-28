package br.com.xmacedo.model;

import br.com.xmacedo.entity.Location;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lat",
    "lng"
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {
    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lng")
    private String lng;

    private Long   idLinha;

    public Location mapBuilder() {
        return Location
            .builder()
            .lat(this.lat)
            .lng(this.lng)
            .build();
    }

}
