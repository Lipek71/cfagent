package cfagent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "partner")
public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
