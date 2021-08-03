package cfagent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "agent")
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
