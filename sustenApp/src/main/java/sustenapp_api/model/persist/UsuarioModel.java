package sustenapp_api.model.persist;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import sustenapp_api.model.type.PerfilTipo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
@Entity
@Table(name = "usuario")
public class UsuarioModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilTipo tipo;

    @Transient
    private List<EnderecoModel> enderecoList;

    @Transient
    private List<TelefoneModel> telefoneList;
}
