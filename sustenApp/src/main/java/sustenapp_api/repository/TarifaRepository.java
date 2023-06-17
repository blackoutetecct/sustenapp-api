package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.TarifaModel;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TarifaRepository extends JpaRepository<TarifaModel, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM tarifa WHERE data = (SELECT MAX(data) FROM tarifa)")
    Optional<TarifaModel> getTopByData();
}
