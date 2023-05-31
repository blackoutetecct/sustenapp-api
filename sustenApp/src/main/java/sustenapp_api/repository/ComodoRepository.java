package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.ComodoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComodoRepository extends JpaRepository<ComodoModel, UUID> {
    Optional<List<ComodoModel>> findAllByUsuario(UUID usuario);
}
