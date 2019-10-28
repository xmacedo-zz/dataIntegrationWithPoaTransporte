package br.com.xmacedo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.xmacedo.model.LaneDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "linha")
public class Lane {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "linha_seq")
    private Long id;

    @Column(name = "idLinhaPoaTransporte")
    private Long idLinhaPoaTransporte;

    @Length(max = 60, min = 2)
    @Column(name = "nome", nullable = false)
    private String nome;

    @Length(max = 8, min = 3)
    @Column(name = "codigo", nullable = false)
    private String codigo;

    public LaneDto buildToLaneDTO() {
        return LaneDto
            .builder()
            .id(this.id)
            .idLinhaPoaTransporte(this.idLinhaPoaTransporte)
            .codigo(this.codigo)
            .nome(this.nome)
            .build();
    }
}
