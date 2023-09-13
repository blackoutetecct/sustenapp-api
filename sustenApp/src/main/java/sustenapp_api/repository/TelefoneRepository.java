package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.persist.TelefoneModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TelefoneRepository extends JpaRepository<TelefoneModel, UUID> {
    Optional<List<TelefoneModel>> findAllByUsuario(UUID usuario);
}
