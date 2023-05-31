package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.EletricidadeModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EletricidadeRepository extends JpaRepository<EletricidadeModel, UUID> {
    Optional<List<EletricidadeModel>> findAllByUsuario(UUID usuario);
}
