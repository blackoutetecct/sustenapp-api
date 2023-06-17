package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.persist.AdministradorModel;

import java.util.UUID;

@Repository
public interface AdministradorRepository extends JpaRepository<AdministradorModel, UUID> {
    boolean existsByEmailAndCpf(String email, String cpf);
    boolean existsByEmailAndSenha(String email, String senha);
}
