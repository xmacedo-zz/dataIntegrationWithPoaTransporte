package br.com.xmacedo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
@Table(name = "localizacao")
public class Location {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="localizacao_seq")
    private Long id;
    private Double lat;
    private Double lng;
}
