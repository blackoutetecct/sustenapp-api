package sustenapp_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "hidricidade")
public class HidricidadeModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Double consumo;
    private UUID usuario;
    private boolean renovavel;
    private Date data;
    private UUID tarifa;

    @Transient
    private Double valorEstimado;

    @Transient
    private Double mediaConsumo;
}
