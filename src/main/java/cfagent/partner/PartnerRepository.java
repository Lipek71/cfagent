package cfagent.partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "partners")
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
