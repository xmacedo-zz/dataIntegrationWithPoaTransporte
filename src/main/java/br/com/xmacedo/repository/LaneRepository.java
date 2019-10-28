package br.com.xmacedo.repository;

import java.util.List;
import java.util.Optional;

import br.com.xmacedo.entity.Lane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LaneRepository extends JpaRepository<Lane, Long> {
    Optional<List<Lane>> findByNomeIgnoreCase(@Param("nome") String nome);

    Optional<Lane> findByIdLinhaPoaTransporte(@Param("idLinhaPoaTransporte") Long idLinhaPoaTransporte);

    @Query("FROM Lane ln join Itinerary it ON ln.idLinhaPoaTransporte = it.idLinha " +
        " join Location lc ON it.idLocalizacao = lc.id  " +
        " WHERE (lc.lat >= :latMin AND lc.lat <= :latMax) " +
        "   AND (lc.lng >= :lonMin AND lc.lng <= :lonMax)")
    List<Lane> findByLatMinAndLatMaxAndLongMinAndLonMax(@Param("latMin")Double latMin, @Param("latMax")Double latMax,
        @Param("lonMin")Double lonMin, @Param("lonMax")Double lonMax);
}
