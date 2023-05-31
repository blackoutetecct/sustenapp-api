package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.DispositivoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DispositivoRepository extends JpaRepository<DispositivoModel, UUID> {
    Optional<List<DispositivoModel>> findAllByUsuario(UUID usuario);
}
