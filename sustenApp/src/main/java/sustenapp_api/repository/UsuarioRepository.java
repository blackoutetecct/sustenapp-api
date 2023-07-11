package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.persist.UsuarioModel;
import sustenapp_api.model.type.PerfilTipo;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {
    boolean existsByEmail(String email);
    boolean existsByEmailOrCpf(String email, String cpf);
    boolean existsByEmailAndSenhaAndTipo(String email, String senha, PerfilTipo tipo);
    boolean existsByCpfAndTipo(String cpf, PerfilTipo tipo);
    Optional<UsuarioModel> findByEmail(String email);
}
