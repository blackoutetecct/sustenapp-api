package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.persist.UsuarioModel;
import sustenapp_api.model.type.PerfilTipo;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndCpf(String email, String cpf);
    boolean existsByEmailAndSenhaAndTipo(String email, String senha, PerfilTipo tipo);
    Optional<UsuarioModel> findByEmail(String email);
}
