package sustenapp_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "preferencia")
public class PreferenciaModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private boolean
            gerenciarEnergiaNaoRenovavel, gerenciarAguaNaoRenovavel,
            gerenciarEnergiaRenovavel, gerenciarAguaRenovavel;

    private UUID usuario;
}
