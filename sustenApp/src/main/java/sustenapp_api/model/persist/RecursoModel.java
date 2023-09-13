package sustenapp_api.model.persist;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import sustenapp_api.model.type.RecursoTipo;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
@Entity
@Table(name = "recurso")
public class RecursoModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Double consumo;
    private boolean renovavel;
    private LocalDateTime data;

    private UUID usuario;
    private UUID tarifa;

    @Enumerated(EnumType.STRING)
    private RecursoTipo tipo;

    @Transient
    private Double valorEstimado;

    @Transient
    private Double mediaConsumo;
}
