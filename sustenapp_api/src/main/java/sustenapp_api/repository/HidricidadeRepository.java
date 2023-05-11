package sustenapp_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sustenapp_api.model.HidricidadeModel;

import java.util.UUID;

@Repository
public interface HidricidadeRepository extends JpaRepository<HidricidadeModel, UUID> {

}
