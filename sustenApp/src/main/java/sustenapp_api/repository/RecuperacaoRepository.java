package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.dynamic.RecuperacaoModel;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecuperacaoRepository extends JpaRepository<RecuperacaoModel, UUID> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndCodigo(String email, String codigo);
    void deleteByEmail(String email);
    Optional<RecuperacaoModel> findByEmail(String email);
}
