package cfagent.partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "partners")
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query("SELECT p FROM Partner p LEFT JOIN FETCH p.agent")
    List<Partner> getPartnerAgentInsurance();
}
