package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.TarifaModel;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TarifaRepository extends JpaRepository<TarifaModel, UUID> {
    Optional<TarifaModel> findFirstByData(Date data);
}
