package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.persist.RecursoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecursoRepository extends JpaRepository<RecursoModel, UUID> {
    Optional<List<RecursoModel>> findAllByUsuario(UUID usuario);

    @Query(nativeQuery = true, value = "SELECT COUNT(recurso.id) FROM eletricidade WHERE renovavel = true")
    long getQuantidadeEletricidadeRenovavel();

    @Query(nativeQuery = true, value = "SELECT SUM(consumo) FROM eletricidade WHERE renovavel = false")
    Optional<Double> getVolumeEletricidade();

    @Query(nativeQuery = true, value = "SELECT SUM(consumo) FROM eletricidade WHERE renovavel = true")
    Optional<Double> getVolumeEletricidadeRenovavel();
}
