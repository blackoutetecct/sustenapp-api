package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.persist.RecursoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecursoRepository extends JpaRepository<RecursoModel, UUID> {
    Optional<List<RecursoModel>> findAllByUsuario(UUID usuario);
    boolean existsByUsuario(UUID usuario);

    @Query(nativeQuery = true, value =
            "SELECT * FROM recurso WHERE usuario = :usuario AND tipo = :tipo AND renovavel = :renovavel AND data = (SELECT MAX(recurso.data) FROM recurso WHERE usuario = :usuario)"
    )
    Optional<RecursoModel> findLast(@Param("usuario") UUID usuario, @Param("tipo") String tipo, @Param("renovavel") boolean renovavel);

    @Query(nativeQuery = true, value = "SELECT COUNT(recurso.id) FROM recurso WHERE tipo = 'HIDRICO' AND renovavel = true")
    long getQuantidadeHidricidadeRenovavel();

    @Query(nativeQuery = true, value = "SELECT SUM(recurso.consumo) FROM recurso WHERE tipo = 'ELETRICO' AND renovavel = false")
    Optional<Double> getVolumeEletricidade();

    @Query(nativeQuery = true, value = "SELECT SUM(recurso.consumo) FROM recurso WHERE tipo = 'HIDRICO' AND renovavel = false")
    Optional<Double> getVolumeHidricidade();

    @Query(nativeQuery = true, value = "SELECT SUM(consumo) FROM recurso WHERE tipo = 'HIDRICO' AND renovavel = true")
    Optional<Double> getVolumeHidricidadeRenovavel();


}
